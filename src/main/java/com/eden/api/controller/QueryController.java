package com.eden.api.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.eden.api.controller.engine.Availability;
import com.eden.api.service.BookingService;
import com.eden.api.service.LocationService;
import com.eden.api.service.UserService;
import com.eden.api.service.validation.LocationQueryValidation;
import com.eden.api.service.validation.ZoneValidation;

import com.eden.api.util.Constants;
import com.eden.api.util.Response;
import com.eden.api.util.ResponseEnum;
import com.edenstar.model.Calendar;
import com.edenstar.model.Kiosk;
import com.edenstar.model.Location;
import com.edenstar.model.Rate;
import com.edenstar.model.User;
import com.edenstar.model.Zone;
import com.edenstar.model.booking.DateSlice;
import com.edenstar.model.booking.GridBuilder;
import com.edenstar.model.booking.KioskQuery;
import com.edenstar.model.booking.QueryLocation;
import com.edenstar.model.dash.CreateLocation;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class QueryController extends BaseController {

	Response response = new Response();
	Availability availability = new Availability();
	LocationQueryValidation locValid = new LocationQueryValidation();

	ZoneValidation zoneValid = new ZoneValidation();

	@Autowired
	private LocationService locationService;

	@Autowired
	private UserService userService;

	@Autowired
	private BookingService bookingService;

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

	private boolean kioskExists(int kioskID) {

		Kiosk kiosk = new Kiosk();
		boolean k_exists = true;

		try {
			System.out.println("kiosk id = " + kioskID);

			kiosk = locationService.getKioskByID(kioskID);
			if (kiosk.getKioskID() == 0)
				k_exists = false;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return k_exists;
	} // kioskExists


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

	@RequestMapping(value = Constants.BASE_PATH + Constants.PATH_BOOKING
			+ Constants.PATH_QUERY_LOCATION, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response queryLocation(@RequestBody QueryLocation l) {

		List<Kiosk> kioskList = new ArrayList<Kiosk>();
		List<Zone> zoneList = new ArrayList<Zone>();
		List<Rate> rateList = new ArrayList<Rate>();
		//List<Booking> bookingList = new ArrayList<Booking>();

		List<KioskQuery> kioskQueryList = new ArrayList<KioskQuery>();

		try {

			// validation routines ...

			// make sure none of the mandatory fields are null
			if (locValid.isNull(l) == true) {
				response = Response.build("Error",
						"mandatory parameters : [staff_email_id / location_id / startDate / endDate] cannot be null",
						false);
				return response;
			} // isNull check

			// check to see if any of the mandatory fields are empty
			if (locValid.fieldsAreEmpty(l) == true) {
				response = Response.build("Error",
						"no data entered for mandatory parameters, either [staff_email_id / location_id / startDate / endDate]",
						false);
				return response;
			} // isEmpty check

			// check to see if the staff user exists on the database
			if (!userExists(l.getStaff_email_id())) {
				response = Response.build("Error",
						"Staff email " + l.getStaff_email_id() + " does not exist on the database", false);
				return response;
			} // staff checks

			// check to see if the locationID exists
			if (!locationExists(l.getLocation_id())) {
				response = Response.build("Error",
						"Location ID " + l.getLocation_id() + " does not exist on the database", false);
				return response;
			} // location checks

			// check dates
			String dateError = locValid.checkDates(l.getStartDate(), l.getEndDate());
			if (dateError == null) {
				// continue as normal ...
			} else {
				response = Response.build("Error", dateError, false);
				return response;
			}

			// all validation have been complete, we now continue to query the location !

			// first we need to get all the information about the location by using the
			// CreateLocation data type !
			CreateLocation getLoc = new CreateLocation();
			getLoc.setLocationID(l.getLocation_id());

			// set location information
			// set the location name
			getLoc.setLocationName(locationService.getLocation(getLoc.getLocationID()).getLocationName());

			// get all the zones relating to the location
			zoneList = locationService.getZone(getLoc);
			getLoc.setZoneList(zoneList);

			// get all rates relating to the location
			rateList = locationService.getRate(getLoc);
			getLoc.setRateList(rateList);

			// get all kiosks relating to a location
			kioskList = locationService.getKiosk(getLoc);
			getLoc.setKioskList(kioskList);

			// get the maximum rows for a given location by location_id
			int max_rows = locationService.getMaxRowsOrColumns(getLoc.getLocationID(), "rows");
			getLoc.setNum_max_rows(max_rows);

			// get the maximum columns for a given location by location_id
			int max_columns = locationService.getMaxRowsOrColumns(getLoc.getLocationID(), "columns");
			getLoc.setNum_max_columns(max_columns);

			// we now have all the information required about the location kiosks
			// we now have to send the GetLoc data and the user data to availability engine
			// to obtain all the relevant booking information and pricing for the dates
			// given
			// this will return a neatly formatted dataset which we can send as a JSON
			// response to the requesting body

			kioskQueryList = new ArrayList<>(processLocation(kioskQueryList, getLoc, l));

			// now we have updated the input dates with all the available dates
			// we have to update the status and colour codes
			availability.processFlags(kioskQueryList);

			// format the dates, JSON uses timezones which changes the dates parsed via
			// JSON, so it is converted into string
			availability.formatDates(kioskQueryList);

			// set the grid row and column maximums
			availability.setGridSize(kioskQueryList, getLoc.getNum_max_rows(), getLoc.getNum_max_columns());

			// we now build the response
			response = Response.build("Success", "Availability for location ID: " + l.getLocation_id()
					+ " for dates from : [" + l.getStartDate() + "] to [" + l.getEndDate() + "]", true);

			// scale back response to preserve cross channel traffic
			List<GridBuilder> gridBuilder = new ArrayList<GridBuilder>();

			System.out.println("size of kioskQueryList = " + kioskQueryList.size());
			gridBuilder = availability.scaleBackJSON(kioskQueryList, gridBuilder);

			// we now set the data and send it back to requesting client
			response.setData(gridBuilder);

		} catch (

		Exception e) {
			response = Response.build(ResponseEnum.INTERNAL_SERVER_ERROR.getStatus(), Constants.INTERNAL_SYSTEM_ERROR,
					ResponseEnum.INTERNAL_SERVER_ERROR.getMessage(), false);
			e.printStackTrace();
		}

		return response;

	} // QueryLocation

	@RequestMapping(value = Constants.BASE_PATH + Constants.PATH_BOOKING
			+ Constants.PATH_QUERY_KIOSK, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response queryKiosk(@RequestBody QueryLocation l) {

		try {

			// validation routines ...

			// make sure none of the mandatory fields are null
			if (locValid.isNull_kiosk(l) == true) {
				response = Response.build("Error",
						"mandatory parameters : [staff_email_id / kiosk_id / startDate / endDate] cannot be null",
						false);
				return response;
			} // isNull check

			// check to see if any of the mandatory fields are empty
			if (locValid.fieldsAreEmpty_kiosk(l) == true) {
				response = Response.build("Error",
						"no data entered for mandatory parameters, either [staff_email_id / kiosk_id / startDate / endDate]",
						false);
				return response;
			} // isEmpty check

			// check to see if the staff user exists on the database
			if (!userExists(l.getStaff_email_id())) {
				response = Response.build("Error",
						"Staff email " + l.getStaff_email_id() + " does not exist on the database", false);
				return response;
			} // staff checks

			// check to see if the locationID exists
			if (!kioskExists(l.getKiosk_id())) {
				response = Response.build("Error", "Kiosk ID " + l.getKiosk_id() + " does not exist on the database",
						false);
				return response;
			} // kiosk checks

			// check dates
			String dateError = locValid.checkDates(l.getStartDate(), l.getEndDate());
			if (dateError == null) {
				// continue as normal ...
			} else {
				response = Response.build("Error", dateError, false);
				return response;
			}

			// make sure that the kiosk is not void or locked
			Kiosk k = new Kiosk();
			k = locationService.getKioskByID(l.getKiosk_id());
			if (k.getIsLocked() == 1 || k.getIsLocked() == 1) {
				response = Response.build("Error", "Kiosk ID " + l.getKiosk_id() + " is not available: locked/void",
						false);
				return response;
			}

			// all validation have been complete, we now continue to query the kiosk !

			// we now have to obtain rate and availablity information about the kiosk

			// stage 1 : obtain rate information by zone id
			Rate r = new Rate();
			r = locationService.getRate(k.getZoneID());

			// stage 2: obtain zone infomration
			Zone z = new Zone();
			z = locationService.getZone(k.getZoneID());

			// stage 3: set the lease duration for pricing calculations
			int leaseLength = availability.getDuration(l.getStartDate(), l.getEndDate());

			// stage 4: create KioskQuery model and set the lease duration
			KioskQuery k_query = new KioskQuery();
			k_query.setLease_duration(leaseLength);

			k_query.setLocation_name(locationService.getLocation(z.getLocationID()).getLocationName().toUpperCase());

			// stage 5: build query model by binding all the information together
			k_query = availability.mapKiosk(k_query, k, r, z);

			// stage 6: create QueryLocation model
			QueryLocation queryLoc = new QueryLocation();
			queryLoc.setStaff_email_id(l.getStaff_email_id());
			queryLoc.setStartDate(l.getStartDate());
			queryLoc.setEndDate(l.getEndDate());
			queryLoc.setLocation_id(z.getLocationID());

			// stage 7: obtain the date availablility for the kiosk
			// here we get the calendar availability for the kiosk
			k_query.setDateList(new ArrayList<DateSlice>(processKioskAvailability(k_query, queryLoc)));

			// stage 8: once we have the availability, we need to find out if all the days
			// are available
			availability.processFlags(k_query);

			// stage 9: format dates
			availability.formatDates(k_query);

			// stage 10: we need to check if all the days are available for the kiosk

			// fully booked ...
			if (k_query.getAvailability_status().contentEquals(Constants.not_avail)) {
				response = Response.build("Attention", "Kiosk ID " + l.getKiosk_id() + " is fully booked", false);
				response.setData(k_query);
				return response;
			}

			// partly booked
			if (k_query.getAvailability_status().contentEquals(Constants.part_avail)) {
				response = Response.build("Attention",
						"Kiosk ID " + l.getKiosk_id() + " is partly booked for dates specified", false);
				response.setData(k_query);
				return response;
			}

			// we now set the data and send it back to requesting client
			response = Response.build("Success", "Availability for kiosk ID: " + l.getKiosk_id() + " for dates from : ["
					+ l.getStartDate() + "] to [" + l.getEndDate() + "]", true);

			// we have slim down the data to save on data traffic loads
			availability.slimDownJSON(k_query);

			response.setData(k_query);

		} catch (

		Exception e) {
			response = Response.build(ResponseEnum.INTERNAL_SERVER_ERROR.getStatus(), Constants.INTERNAL_SYSTEM_ERROR,
					ResponseEnum.INTERNAL_SERVER_ERROR.getMessage(), false);
			e.printStackTrace();
		}

		return response;

	} // QueryKiosk

	private List<KioskQuery> processLocation(List<KioskQuery> k_List, CreateLocation loc, QueryLocation l) {

		// we must go through each kiosk and map the information in the KioskQuery
		// object

		int leaseLength = availability.getDuration(l.getStartDate(), l.getEndDate());

		for (int i = 0; i < loc.getKioskList().size(); i++) {

			KioskQuery k = new KioskQuery();
			k.setLease_duration(leaseLength);
			k = availability.mapKiosk(k, loc.getKioskList().get(i), loc.getRateList(), loc.getZoneList());
			// set the location name
			k.setLocation_name(loc.getLocationName().toUpperCase());

			// now that we have the kiosk information, we first need to check
			// the kiosk's status (void or locked) and set the colour code and status
			// accordingly in k

			if ((k.getIsLocked() == 0) && (k.getIsVoid() == 0)) {
				// here we get the calendar availability for the kiosk
				k.setDateList(new ArrayList<DateSlice>(processKioskAvailability(k, l)));

			}

			if (k.getIsLocked() == 1) {
				k.setAvailability_status(Constants.lock_avail);
				k.setAvailability_color(Constants.lock_avail_colour);
			}

			if (k.getIsVoid() == 1) {
				k.setAvailability_status(Constants.void_avail);
				k.setAvailability_color(Constants.void_avail_colour);
			}

			// now we add the kioskQuery list
			k_List.add(k);

		} // outtermost for loop

		return k_List;
	} // processLocation

	private List<ArrayList<DateSlice>> getKioskCalendar(KioskQuery k, QueryLocation l) {
		// The booking calender for each kiosk is consulted against the
		// required booking dates, and the results of the calendar are
		// attached to the kiosk
		List<Calendar> bookingCalendar = new ArrayList<Calendar>();
		List<ArrayList<DateSlice>> bookingList = new ArrayList<ArrayList<DateSlice>>();

		try {

			bookingCalendar = new ArrayList<>(bookingService.getBookingsForKiosk(k.getKioskID()));

			// once the booking calender has been populated we have to convert the dates
			// into
			// DateSlices and add the list of DateSlice to another List called bookingList
			int i = 0;
			for (i = 0; i < bookingCalendar.size(); i++) {

				ArrayList<DateSlice> dateListDB = new ArrayList<DateSlice>();

				dateListDB = new ArrayList<>(availability.getDateSliceList(bookingCalendar.get(i)));
				bookingList.add(dateListDB);

			} // for

		} catch (Exception e) {

			e.printStackTrace();
		}

		return bookingList;

	} // getKioskCalendar

	private List<DateSlice> processKioskAvailability(KioskQuery k, QueryLocation l) {
		// we now generate an array of DateSlices from the input JSON date range to
		// perform our date comparison operations
		// first we need to covert the input String dates into DateTime type objects
		List<DateSlice> dateListIn = new ArrayList<DateSlice>();

		String startDateIn = l.getStartDate();
		String endDateIn = l.getEndDate();

		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

		Date startDate = null;
		Date endDate = null;

		try {

			startDate = format.parse(startDateIn);
			endDate = format.parse(endDateIn);

			DateTime start_date = new DateTime(startDate);
			DateTime end_date = new DateTime(endDate);

			// we now generate an array of DateSlices to perform our date operations
			// dateListIn = availability.generateDateList(start_date, end_date);
			dateListIn = new ArrayList<DateSlice>(availability.generateDateList(start_date, end_date));

			// first step : convert incoming date range into a list of dates
			// we need to know the start date and end date then calculate the number days
			List<ArrayList<DateSlice>> kioskBookingList = getKioskCalendar(k, l);

			// we now have the dateslice array for the user's input and the kiosk's bookings
			// in dateslice format. We need to send both of these to the availability
			// utility
			// which would compare the each date in the input datesclice array with all the
			// dates
			// in the bookingList array to see if any matches are present, if they are then
			// availability flag for that particular day is set to false and the calender_id
			// is assigned.

			dateListIn = new ArrayList<DateSlice>(availability.checkAvailability(dateListIn, kioskBookingList));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return dateListIn;

	} // processKioskAvailability

} // QueryController
