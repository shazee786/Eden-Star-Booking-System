package com.eden.api.controller;

import java.util.ArrayList;
import java.util.List;

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
import com.edenstar.model.Kiosk;
import com.edenstar.model.Location;
import com.edenstar.model.Rate;
import com.edenstar.model.User;
import com.edenstar.model.Zone;
import com.edenstar.model.dash.CreateZone;

@CrossOrigin(origins="*", allowedHeaders="*")
@RestController
public class ZoneController extends BaseController {

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

	private boolean locationExists(int location_id) {

		Location r = new Location();
		boolean c_exists = true;

		try {

			r = locationService.getLocation(location_id);

			// if the record does not exist we should return false
			if (r.getLocationName() == null)
				return false;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return c_exists;

	} // locationExists

	private boolean zoneExists(int zone_id) {

		Zone z = new Zone();
		boolean c_exists = true;

		try {

			z = locationService.getZone(zone_id);

			// if the record does not exist we should return false
			if (z.getZoneNumber() == 0)
				return false;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return c_exists;

	} // zoneExists

	private void copyStoredZone(CreateZone z) {
		Zone zone = new Zone();
		try {

			zone = locationService.getZone(z.getZoneID());

			// we now need to copy over the stored information if no new information is
			// entered
			z.setLocationID(zone.getLocationID());

			// if no zone_number is specified port over the stored one
			if (z.getZoneNumber() == 0) {
				z.setZoneNumber(zone.getZoneNumber());
			}

			// if no zone_name is specfied, copy the stored one
			if (z.getZoneName().contentEquals("") || z.getZoneName() == null) {
				z.setZoneName(zone.getZoneName());
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	} // copyStoreZone

	private boolean newZoneNumOK(CreateZone z) {

		Zone zone = new Zone();
		boolean checkZoneNumber = false;

		try {

			zone = locationService.checkZoneNum(z);
			System.out.println("zone id = " + zone.getZoneID());

			// if the zone_ids are the same return true
			if (zone.getZoneID() == z.getZoneID())
				return true;

			// if the record does not exist we should return true
			if (zone.getZoneID() == 0)
				checkZoneNumber = true;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return checkZoneNumber;

	} // zoneExists

	// **************************************************************************************
	// add a zone and corresponding rate to a existing location
	// **************************************************************************************
	@RequestMapping(value = Constants.BASE_PATH + Constants.PATH_DASH
			+ Constants.PATH_ADD_ZONE, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response addZone(@RequestBody CreateZone z) {
		String error = "";

		try {

			// make sure none of the mandatory fields are null
			if (zoneValid.isNull(z) == true) {
				response = Response.build("Error",
						"mandatory parameters : [staff_email / locationID / rate ] cannot be null", false);
				return response;
			} // isNull check

			// check to see if any of the mandatory fields are empty
			if (zoneValid.fieldsAreEmpty(z) == true) {
				response = Response.build("Error",
						"no data entered for mandatory parameters, either [staff_email / locationID / rate ]", false);
				return response;
			} // isEmpty check

			// we need to check that the rate has all fields within a valid range
			if (!zoneValid.validateRates(z)) {
				response = Response.build("Error", "rate data entered not with valid range", false);
				return response;
			} // validateRates

			// check to see if the staff user exists on the database
			if (!userExists(z.getStaff_email_id())) {
				response = Response.build("Error", "staff email id " + z.getStaff_email_id() + " does not exist",
						false);
				return response;
			} // userExists

			// check if the staff user has clearance to perform this action
			if (checkClearance(z.getStaff_email_id().toLowerCase()) == false) {

				// insufficient priviledges
				response = Response.build("Access Denied",
						"insufficient privileges - you must be either be admin or manager_admin to perform this action",
						false);
				return response;

			} // clearance check

			// check to see if the locationID exists
			if (!locationExists(z.getLocationID())) {

				response = Response.build("Error",
						"Location id" + z.getLocationID() + " does not exist on the database", false);
				return response;

			} // locationExists

			// once all the validations have been completed we can go ahead and add the zone
			// but first we need to obtain the next available zone number
			int zone_number = locationService.getMaxZoneNum(z.getLocationID()) + 1; // plus 1 because want the next zone
																					// number for new zone
			z.setZoneNumber(zone_number);

			// we now have a zone number, we now need to create a new zone
			int zone_id = locationService.addZone(z);

			if (zone_id > 0) {

				// set the zone id
				z.setZoneID(zone_id);

				// set the zone_id to rate
				z.getRate().setZoneID(zone_id);

				// create rate ...
				int rate_id = locationService.addRate(z);

				// now we check to see if the rate_id was successfully generated
				if (rate_id > 0) {

					// set the rate_id
					z.getRate().setRateID(rate_id);

				} else {

					error = error + " [rate_id for zone number " + z.getZoneNumber() + " has not been generated.] ";

				} // if rate_id

			} else {

				error = error + " [zone_id for zone number " + z.getZoneNumber() + " has not been generated.] ";

			} // if zone_id

		} catch (

		Exception e) {
			response = Response.build(ResponseEnum.INTERNAL_SERVER_ERROR.getStatus(), Constants.INTERNAL_SYSTEM_ERROR,
					ResponseEnum.INTERNAL_SERVER_ERROR.getMessage(), false);
			e.printStackTrace();
		}

		if (error.contentEquals("")) {
			response = Response.build("Success", "Zone and Rate were successfully generated on database", true);
			response.setData(z);
		} else {
			System.out.println(error);
			response = Response.build("Failure", error, false);
		}

		return response;

	} // add a zone and corresponding rate

	// **************************************************************************************
	// get all zone information for given zone_id
	// **************************************************************************************
	@RequestMapping(value = Constants.BASE_PATH + Constants.PATH_DASH
			+ Constants.PATH_GET_ZONE, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getZone(@RequestBody CreateZone z) {

		List<Kiosk> kioskList = new ArrayList<Kiosk>();
		Rate rate = new Rate();
		Zone zone = new Zone();

		try {

			// make sure that the staff_email and locationID are not null
			if (z.getStaff_email_id() == null || z.getZoneID() == 0) {
				response = Response.build("Error", "Mandatory field staff_email / zoneID cannot be null", false);
				return response;
			} // null fields

			// check to see if the fields are empty
			if (z.getStaff_email_id().contentEquals("") || z.getZoneID() == 0) {
				response = Response.build("Error", "Mandatory field : staff_email / zoneID cannot be empty", false);
				return response;
			} // empty fields

			// check to see if the staff user exists on the database
			if (!userExists(z.getStaff_email_id())) {
				response = Response.build("Error",
						"Staff email " + z.getStaff_email_id() + " does not exist on the database", false);
				return response;
			} // staff checks

			// check to see if the zoneID exists
			if (!zoneExists(z.getZoneID())) {
				response = Response.build("Error", "Zone ID " + z.getZoneID() + " does not exist on the database",
						false);
				return response;
			} // zone checks

			// get zone information
			zone = locationService.getZone(z.getZoneID());
			z.setZoneName(zone.getZoneName());
			z.setZoneNumber(zone.getZoneNumber());
			z.setLocationID(zone.getLocationID());

			// gets the corresponding rate information relating to the zone id
			rate = locationService.getRate(z.getZoneID());
			z.setRate(rate);

			// get all kiosks relating to a location
			kioskList = locationService.getKiosk(z.getZoneID());
			z.setKioskList(kioskList);

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
	// DELETE a zone and all associated kiosks and the corresponding rate
	// **************************************************************************************
	@RequestMapping(value = Constants.BASE_PATH + Constants.PATH_DASH
			+ Constants.PATH_DELETE_ZONE, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response deleteZone(@RequestBody CreateZone z) {

		try {
			// make sure that the staff_email and locationID are not null
			if (z.getStaff_email_id() == null || z.getZoneID() == 0) {
				response = Response.build("Error", "Mandatory field staff_email / zoneID cannot be null", false);
				return response;
			} // null fields

			// check to see if the fields are empty
			if (z.getStaff_email_id().contentEquals("") || z.getZoneID() == 0) {
				response = Response.build("Error", "Mandatory field : staff_email / zoneID cannot be empty", false);
				return response;
			} // empty fields

			// check to see if the staff user exists on the database
			if (!userExists(z.getStaff_email_id())) {
				response = Response.build("Error",
						"Staff email " + z.getStaff_email_id() + " does not exist on the database", false);
				return response;
			} // staff checks

			// check to see if the locationID exists
			if (!zoneExists(z.getZoneID())) {
				response = Response.build("Error", "Location ID " + z.getZoneID() + " does not exist on the database",
						false);
				return response;
			} // zone checks

			// check if the staff user has clearance to perform this action
			if (checkClearance(z.getStaff_email_id().toString().toLowerCase()) == false) {

				// insufficient priviledges
				response = Response.build("Access Denied",
						"insufficient privileges - you must be either be admin or manager_admin to perform this action",
						false);
				return response;

			} // clearance check

			// delete zone and all associated kiosks and corresponding rate
			int status = locationService.deleteZone(z.getZoneID());

			if (status == 1) {
				response = Response.build("Success",
						"zone and associated kiosks and rate successfully deleted from the database", true);

			} else {
				response = Response.build("Failure",
						"Zone of id " + z.getZoneID() + " could not be deleted from the database", false);

			} // nested if

		} catch (

		Exception e) {

			e.printStackTrace();
		} // try-catch
		return response;

	} // deleteZone

	// **************************************************************************************
	// update zone information to the database
	// **************************************************************************************
	@RequestMapping(value = Constants.BASE_PATH + Constants.PATH_DASH
			+ Constants.PATH_UPDATE_ZONE, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateZone(@RequestBody CreateZone z) {

		try {

			// make sure that the staff_email and zoneID are not null
			if (z.getStaff_email_id() == null || z.getZoneID() == 0) {
				response = Response.build("Error", "Mandatory field staff_email_id / zoneID cannot be null", false);
				return response;
			} // null fields

			// check to see if the fields are empty
			if (z.getStaff_email_id().contentEquals("") || z.getZoneID() == 0) {
				response = Response.build("Error", "Mandatory field : staff_email_id / zoneID cannot be empty", false);
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

			// check to see if the zoneID exists
			if (!zoneExists(z.getZoneID())) {
				response = Response.build("Error", "Zone ID " + z.getZoneID() + " does not exist on the database",
						false);
				return response;
			} // zone checks

			// of the zone number is changed we have to make sure that it does not conflict
			// with other
			// zone numbers under the same location

			// first check if there is a zone number specified ...
			if (z.getZoneNumber() != 0) {

				if (!newZoneNumOK(z)) {
					// there is a conflict
					response = Response.build("Error",
							"New zone number " + z.getZoneNumber() + " conflicts with another on the database", false);
					return response;

				} // nested if
			} // outer if

			// we need to copy over the zone information from the database
			// and update the new information
			copyStoredZone(z);

			// now that all the check have been performed, we can go ahead and update zone
			// with new data
			int status = locationService.updateZone(z);

			if (status == 1) {
				response = Response.build("Success", "Zone details successfully updated to the database", true);
			} else {
				response = Response.build("Error", "Zone details could not be updated to the database", false);
			} // nested if

		} catch (

		Exception e) {
			response = Response.build(ResponseEnum.INTERNAL_SERVER_ERROR.getStatus(), Constants.INTERNAL_SYSTEM_ERROR,
					ResponseEnum.INTERNAL_SERVER_ERROR.getMessage(), false);
			e.printStackTrace();
		}

		return response;

	} // updateZone

} // ZoneController
