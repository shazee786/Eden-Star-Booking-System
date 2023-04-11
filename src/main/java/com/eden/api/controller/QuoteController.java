package com.eden.api.controller;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
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
import com.eden.api.controller.engine.KeyGenerator;
import com.eden.api.service.BookingService;
import com.eden.api.service.CustomerService;
import com.eden.api.service.LocationService;
import com.eden.api.service.UserService;
import com.eden.api.service.validation.CustomerValidation;
import com.eden.api.service.validation.LocationQueryValidation;
import com.eden.api.service.validation.ZoneValidation;
import com.eden.api.util.Constants;
import com.eden.api.util.Response;
import com.eden.api.util.ResponseEnum;
import com.edenstar.model.Calendar;
import com.edenstar.model.Customer;
import com.edenstar.model.Kiosk;
import com.edenstar.model.Rate;
import com.edenstar.model.User;
import com.edenstar.model.Zone;
import com.edenstar.model.booking.AddQuote;
import com.edenstar.model.booking.DateSlice;
import com.edenstar.model.booking.KioskQuery;
import com.edenstar.model.booking.QueryLocation;
import com.edenstar.model.booking.forms.QuotationForm;
import com.edenstar.model.dash.CreateCompany;
import com.edenstar.model.dash.CreateCustomer;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class QuoteController extends BaseController {

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

	@Autowired
	private CustomerService customerService;

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

	private boolean kioskExists(int kiosk_id) {

		Kiosk kiosk = new Kiosk();
		boolean k_exists = true;

		try {

			kiosk = locationService.getKioskByID(kiosk_id);
			if (kiosk.getKioskID() == 0)
				k_exists = false;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return k_exists;
	} // kioskExists

	@RequestMapping(value = Constants.BASE_PATH + Constants.PATH_BOOKING
			+ Constants.PATH_ADD_QUOTE, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response addQuotation(@RequestBody AddQuote q) {

		try {
			// validation routines ...

			// make sure none of the mandatory fields are null
			if (locValid.isNull(q) == true) {
				response = Response.build("Error",
						"mandatory parameters : [staff_email_id / kiosk_id / startDate / endDate / emailIDCus / firstName / lastName] cannot be null",
						false);
				return response;
			} // isNull check

			// check to see if any of the mandatory fields are empty
			if (locValid.fieldsAreEmpty(q) == true) {
				response = Response.build("Error",
						"no data entered for mandatory parameters, either [staff_email_id / kiosk_id / startDate / endDate]",
						false);
				return response;
			} // isEmpty check

			// check to see if the staff user exists on the database
			if (!userExists(q.getStaff_email_id())) {
				response = Response.build("Error",
						"Staff email " + q.getStaff_email_id() + " does not exist on the database", false);
				return response;
			} // staff checks

			// check to see if the kiosk exists
			if (!kioskExists(q.getKiosk_id())) {
				response = Response.build("Error", "Kiosk ID " + q.getKiosk_id() + " does not exist on the database",
						false);
				return response;
			} // kiosk checks

			// check dates
			String dateError = locValid.checkDates(q.getStartDate(), q.getEndDate());
			if (dateError == null) {
				// continue as normal ...
			} else {
				response = Response.build("Error", dateError, false);
				return response;
			}

			// make sure that the kiosk is not void or locked
			Kiosk k = new Kiosk();
			k = locationService.getKioskByID(q.getKiosk_id());
			if (k.getIsLocked() == 1 || k.getIsLocked() == 1) {
				response = Response.build("Error", "Kiosk ID " + q.getKiosk_id() + " is not available: locked/void",
						false);
				return response;
			}

			// we now have to obtain rate and availablity information about the kiosk

			// stage 1 : obtain rate information by zone id
			Rate r = new Rate();
			r = locationService.getRate(k.getZoneID());

			// stage 2: obtain zone infomration
			Zone z = new Zone();
			z = locationService.getZone(k.getZoneID());

			// stage 3: set the lease duration for pricing calculations
			int leaseLength = availability.getDuration(q.getStartDate(), q.getEndDate());

			// stage 4: create KioskQuery model and set the lease duration
			KioskQuery k_query = new KioskQuery();
			k_query.setLease_duration(leaseLength);

			k_query.setLocation_name(locationService.getLocation(z.getLocationID()).getLocationName().toUpperCase());

			// stage 5: build query model by binding all the information together
			k_query = availability.mapKiosk(k_query, k, r, z);

			// stage 6: create QueryLocation model
			QueryLocation l = new QueryLocation();
			l.setStaff_email_id(q.getStaff_email_id());
			l.setStartDate(q.getStartDate());
			l.setEndDate(q.getEndDate());
			l.setLocation_id(z.getLocationID());

			// stage 7: obtain the date availablility for the kiosk
			// here we get the calendar availability for the kiosk
			k_query.setDateList(new ArrayList<DateSlice>(processKioskAvailability(k_query, l)));

			// stage 8: once we have the availability, we need to find out if all the days
			// are available
			availability.processFlags(k_query);

			// stage 9: format dates
			availability.formatDates(k_query);

			// stage 10: we need to check if all the days are available for the kiosk

			// fully booked ...
			if (k_query.getAvailability_status().contentEquals(Constants.not_avail)) {
				response = Response.build("Attention", "Kiosk ID " + q.getKiosk_id() + " is fully booked", false);
				response.setData(k_query);
				return response;
			}

			// partly booked
			if (k_query.getAvailability_status().contentEquals(Constants.part_avail)) {
				response = Response.build("Attention",
						"Kiosk ID " + q.getKiosk_id() + " is partly booked for dates specified", false);
				response.setData(k_query);
				return response;
			}

			// now that we have available dates, we assign it to the addQuote data type
			q.setQ_kiosk(k_query);

			// stage 11 : check whether the user customer email exists on the database ...
			Customer c = new Customer();
			c = customerService.getCustDetails(q.getEmailIDCus().toLowerCase());

			if (c.getCustomerID() < 1) {
				// customer does not exist, need to create one with basic information ...
				CreateCustomer customer = new CreateCustomer();
				availability.mapCustomer(customer, q);

				// format the fields
				CustomerValidation cusValid = new CustomerValidation();
				customer = cusValid.formatFields(customer);

				// we now send the customer information to the database and obtain a customer id
				int customer_id = customerService.addCustomer(customer);
				System.out.println("****** the generated customer id is *****" + customer_id);

				if (customer_id > 0) {
					// response = Response.build("Success", "customer successfully added to the
					// database", true);

					// set the customer id in the company object
					customer.getCompany().setCustomerID(customer_id);

					// we have to add the company name if one is specified
					if (q.getCompanyName() == null) {

					} else {
						customer.getCompany().setCompanyName(q.getCompanyName());
					}

					// set the customer id in the customer object
					customer.setCustomerID(customer_id);

					// we now have to add the company
					int company_id = customerService.addCompany(customer);
					System.out.println(" the generated company id is = " + company_id);

					if (company_id > 0) {
						System.out.println("Customer and company successfully added");
						customer.getCompany().setCompanyID(company_id);

						// copy over the customer information
						availability.mapQuote(q, customer);

						// copy over company info
						q.setCompanyList(customerService.getCustComp(q.getCustomerID()));

						response.setData(q);

					} else {
						response = Response.build("Failure", "company could not be added to the database", false);
						return response;
					}

				} else {
					response = Response.build("Failure", "customer could not be added to the database", false);
					return response;
				} // nested if

			} else {
				// port customer details over

				availability.mapCustomer(q, c);

				// copy over company info
				q.setCompanyList(customerService.getCustComp(q.getCustomerID()));

				// if the customer specifies a new company name, this should be added to the
				// list of companies
				boolean isNewCompany = true;

				if (q.getCompanyName() == null) {

				} else {

					for (int i = 0; i < q.getCompanyList().size(); i++) {

						if (q.getCompanyName().toLowerCase()
								.equals(q.getCompanyList().get(i).getCompanyName().toLowerCase())) {
							// set isNewCompany to false
							isNewCompany = false;
						} // if
					} // for
					
					if (isNewCompany == true) {
						// create the new company
						// set the customer id in the customer object
						CreateCompany addCompany = new CreateCompany();
						addCompany.setCompanyName(q.getCompanyName());
						addCompany.setCustomerID(q.getCustomerID());
						addCompany.setEmail_id_cus(q.getEmailIDCus());

						// we now have to add the company
						int company_id = customerService.addAnotherCompany(addCompany);
						System.out.println(" the generated company id is = " + company_id);

						if (company_id > 0) {
							System.out.println("New company successfully added, company id = " + company_id);

							// copy over company info
							q.setCompanyList(customerService.getCustComp(q.getCustomerID()));

							response.setData(q);

						} else {
							response = Response.build("Failure", "company could not be added to the database", false);
							return response;
						}
						
					}
					
				} // if outer
			}

			// now we have all the necessary information, we now need to commit the
			// information to the
			// database and obtain the quote id

			// we first need to obtain the employee id
			User u = userService.getUserDetails(q.getStaff_email_id());
			q.setEmployee_id(u.getEmployeeID());

			// we need to set the current date - the date of the quote
			Instant instant = Instant.now();
			ZoneId zoneId_Dubai = ZoneId.of("Asia/Dubai");
			ZonedDateTime zdt_Dubai = ZonedDateTime.ofInstant(instant, zoneId_Dubai);

			q.setDate_of_quote(zdt_Dubai.toLocalDate());
			
			// we now add a unique reference key for the customer
			KeyGenerator keyGen = new KeyGenerator();
			String uniqueRef = keyGen.generateKey(q.getFirstName(), q.getLastName());
			q.setQuoteRef(uniqueRef);
			
			// set the quote expiry in days
			q.setExpiry_duration_days(Constants.quotation_valid_for_days);

			int quote_id = bookingService.addQuote(q);

			if (quote_id < 1) {
				// quote not successfully added to the database
				response = Response.build("Failure", "Quotation failed to be added to the database", false);
				response.setData(q);
				return response;
			}

			// if the quote was succesfull we update with quote_id
			System.out.println("*** Generated quote ID = " + quote_id + " ***");

			// assign the quote to the addQuote object
			q.setQuote_id(quote_id);

			// we now have to email the quotation form to the customer
			User staff_details = userService.getUserDetails(q.getStaff_email_id());
			QuotationForm quoteForm = availability.generateQuoteEmail(q, staff_details);
			//QuotationFormNew quoteForm = new QuotationFormNew();
			String messageBody = quoteForm.toString();

			// we now have to email the quotation to the customer
			boolean emailSent = bookingService.emailQuoteToCustomer(q.getEmailIDCus(), messageBody);

			// all data has been generated, we can now email the quote to the customer
			response = Response.build("Success", "Quotation successfully generated and emailed to the customer", emailSent);
			response.setData(q);

		} catch (

		Exception e) {
			response = Response.build(ResponseEnum.INTERNAL_SERVER_ERROR.getStatus(), Constants.INTERNAL_SYSTEM_ERROR,
					ResponseEnum.INTERNAL_SERVER_ERROR.getMessage(), false);
			e.printStackTrace();
		}

		return response;

	} // addQuote

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

} // QuoteController
