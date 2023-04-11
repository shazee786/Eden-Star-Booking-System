package com.eden.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.eden.api.service.LocationService;
import com.eden.api.service.UserService;
import com.eden.api.service.validation.ZoneValidation;
import com.eden.api.util.Constants;
import com.eden.api.util.Response;
import com.eden.api.util.ResponseEnum;
import com.edenstar.model.Rate;
import com.edenstar.model.User;
import com.edenstar.model.Zone;
import com.edenstar.model.dash.CreateZone;

@CrossOrigin(origins="*", allowedHeaders="*")
@RestController
public class RateController extends BaseController {

	Response response = new Response();

	ZoneValidation zoneValid = new ZoneValidation();

	@Autowired
	private LocationService locationService;

	@Autowired
	private UserService userService;

	// check admin or manager level
	private boolean checkClearance(String staff_email) {

		User r = new User();

		try {

			r = userService.getUserDetails(staff_email);

			if (r.getUserLevel().contentEquals("manager_admin")) {

				System.out.println("manager clearance accepted");
				return true;

			} else if (r.getUserLevel().equals("admin")) {

				System.out.println("administrator level clearance verified");
				return true;

			} else
				return false;

		} catch (Exception e) {

			e.printStackTrace();
		}

		return false;

	} // checkClearance

	private boolean userExists(String staff_email) {

		User r = new User();
		boolean u_exists = true;

		try {
			System.out.println("staff_email = " + staff_email);

			r = userService.getUserDetails(staff_email);
			if (r.getEmailID() == null)
				return false;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return u_exists;

	} // userExists


	private boolean rateExists(CreateZone z) {
		Rate r = new Rate();
		boolean r_exists = true;

		try {

			r = locationService.getRateByRateID(z.getRate().getRateID());

			// if the record does not exist we should return false
			if (r.getRateID() == 0)
				return false;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// if the rate does exist, we can assign the zone_id to it
		z.setZoneID(r.getZoneID());

		return r_exists;
	} // RateExists

	// **************************************************************************************
	// get all rate information for given rate_id
	// **************************************************************************************
	@RequestMapping(value = Constants.BASE_PATH + Constants.PATH_DASH
			+ Constants.PATH_GET_RATE, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getRate(@RequestBody CreateZone z) {

		Rate rate = new Rate();
		Zone zone = new Zone();

		try {

			// make sure that the staff_email and rateID are not null
			if (z.getStaff_email_id() == null || z.getRate().getRateID() == 0) {
				response = Response.build("Error", "Mandatory field staff_email / rateID cannot be null", false);
				return response;
			} // null fields

			// check to see if the fields are empty
			if (z.getStaff_email_id().contentEquals("") || z.getRate().getRateID() == 0) {
				response = Response.build("Error", "Mandatory field : staff_email / rateID cannot be empty", false);
				return response;
			} // empty fields

			// check to see if the staff user exists on the database
			if (!userExists(z.getStaff_email_id())) {
				response = Response.build("Error",
						"Staff email " + z.getStaff_email_id() + " does not exist on the database", false);
				return response;
			} // staff checks

			// check to see if the rateID exists
			if (!rateExists(z)) {
				response = Response.build("Error",
						"Rate ID " + z.getRate().getRateID() + " does not exist on the database", false);
				return response;
			} // rate checks

			// the zone_id is copied over from the database if the rate_id exists (by the
			// rateExists method)

			// get zone information
			zone = locationService.getZone(z.getZoneID());
			z.setZoneName(zone.getZoneName());
			z.setZoneNumber(zone.getZoneNumber());
			z.setLocationID(zone.getLocationID());

			// gets the corresponding rate information relating to the zone id
			rate = locationService.getRate(z.getZoneID());

			z.setRate(rate);

			// kiosk information has been diabled for this function because it is not
			// relevant here
//			// get all kiosks relating to a location
//			kioskList = locationService.getKiosk(z.getZoneID());
//			z.setKioskList(kioskList);

			response = Response.build(ResponseEnum.OK.getStatus(), Constants.SUCCESS, ResponseEnum.OK.getMessage(),
					true);

			response.setData(z);

		} catch (

		Exception e) {
			response = Response.build(ResponseEnum.INTERNAL_SERVER_ERROR.getStatus(), Constants.INTERNAL_SYSTEM_ERROR,
					ResponseEnum.INTERNAL_SERVER_ERROR.getMessage(), false);
			e.printStackTrace();
		}

		return response;

	} // getZone

	// **************************************************************************************
	// update rate information to the database
	// **************************************************************************************
	@RequestMapping(value = Constants.BASE_PATH + Constants.PATH_DASH
			+ Constants.PATH_UPDATE_RATE, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateRate(@RequestBody CreateZone z) {

		try {

			// make sure that the staff_email and rateID are not null
			if (z.getStaff_email_id() == null || z.getRate().getRateID() == 0) {
				response = Response.build("Error", "Mandatory field staff_email_id / rateID cannot be null", false);
				return response;
			} // null fields

			// check to see if the fields are empty
			if (z.getStaff_email_id().contentEquals("") || z.getRate().getRateID() == 0) {
				response = Response.build("Error", "Mandatory field : staff_email_id / rateID cannot be empty", false);
				return response;
			} // empty fields

			// check to see if the staff user exists on the database
			if (!userExists(z.getStaff_email_id())) {
				response = Response.build("Error",
						"Staff email " + z.getStaff_email_id() + " does not exist on the database", false);
				return response;
			} // staff checks
			
			
			// check if the staff user has clearance to perform this action
			if (checkClearance(z.getStaff_email_id().toString().toLowerCase()) == false) {

				// insufficient priviledges
				response = Response.build("Access Denied",
						"insufficient privileges - you must be either be admin or manager_admin to perform this action",
						false);
				return response;

			} // clearance check

			// check to see if the rateID exists
			if (!rateExists(z)) {
				response = Response.build("Error", "Zone ID " + z.getZoneID() + " does not exist on the database",
						false);
				return response;
			} // zone checks

			// we need compare and check the rate information provided against the database
			// values
			// and copy database stored values if CreateZone z does not contain them
			copyRateValues(z);

			// we now have the rate values, we have to validate them
			// we need to check that the rate has all fields within a valid range
			if (!zoneValid.validateRates(z)) {
				response = Response.build("Error", "rate data entered not with valid range", false);
				return response;
			} // validateRates

			// once the rate values have been validated, we can commit the changes to the
			// database
			int status = locationService.updateRate(z);

			if (status == 1) {
				response = Response.build("Success", "Rate details successfully updated to the database", true);
			} else {
				response = Response.build("Error", "Rate details could not be updated to the database", false);
			} // nested if

		} catch (

		Exception e) {
			response = Response.build(ResponseEnum.INTERNAL_SERVER_ERROR.getStatus(), Constants.INTERNAL_SYSTEM_ERROR,
					ResponseEnum.INTERNAL_SERVER_ERROR.getMessage(), false);
			e.printStackTrace();
		}

		return response;

	} // updateZone

	private void copyRateValues(CreateZone z) {

		// we first need to obtain the rate values over from the
		// database
		Rate r = new Rate();
		try {

			// now that we have the rate from the database
			r = locationService.getRateByRateID(z.getRate().getRateID());

			// we check the three variables rate_max, rate_min and duration to see if
			// there are any changes ... if not copy over the database value

			if (z.getRate().getRateMax() == 0)
				z.getRate().setRateMax(r.getRateMax());

			if (z.getRate().getRateMin() == 0)
				z.getRate().setRateMin(r.getRateMin());

			if (z.getRate().getDiscountDurationDays() == 0)
				z.getRate().setDiscountDurationDays(r.getDiscountDurationDays());

			// we need to copy over the non-changable values such as zone_id
			z.getRate().setZoneID(r.getZoneID());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	} // copyRateValues

} // RateController
