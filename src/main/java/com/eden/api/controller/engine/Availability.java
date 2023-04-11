package com.eden.api.controller.engine;

import org.joda.time.DateTime;
import org.joda.time.Days;

import com.eden.api.util.Constants;
import com.edenstar.model.Calendar;
import com.edenstar.model.Customer;
import com.edenstar.model.Kiosk;
import com.edenstar.model.Rate;
import com.edenstar.model.User;
import com.edenstar.model.Zone;
import com.edenstar.model.booking.AddQuote;
import com.edenstar.model.booking.DateSlice;
import com.edenstar.model.booking.GridBuilder;
import com.edenstar.model.booking.KioskQuery;
import com.edenstar.model.booking.forms.QuotationForm;
import com.edenstar.model.dash.CreateCustomer;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Availability {

	public List<DateSlice> checkAvailability(List<DateSlice> inputDates, List<ArrayList<DateSlice>> bookingList) {

		// here we check each dateSlice in the userDates list against all the DateSlices
		// in the
		// bookingList, if there is a match we set the flag and assign the calender id

		for (int i = 0; i < inputDates.size(); i++) {

			for (int j = 0; j < bookingList.size(); j++) {

				for (int k = 0; k < bookingList.get(j).size(); k++) {

					// compare the dates
					if (bookingList.get(j).get(k).getDate().equals(inputDates.get(i).getDate())) {

						inputDates.get(i).setAvailable(false);
						inputDates.get(i).setCalenderID(bookingList.get(j).get(k).getCalenderID());

					} // if statement

				} // innermost for loop

			} // inner for loop

		} // outer for loop

		return inputDates;
	} // checkAvailability

	public List<DateSlice> generateDateList(DateTime startDate, DateTime endDate) throws Exception {

		List<DateSlice> dateList = new ArrayList<DateSlice>();
		// SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		int i = 0;
		int durationPeriod = 0;

		try {

			durationPeriod = Days.daysBetween(startDate, endDate).getDays() + 1;

			for (i = 0; i < durationPeriod; i++) {

				DateSlice d = new DateSlice();
				DateTime dateTime = new DateTime(startDate);

				d.setAvailable(true); // availability flag set to true by default;

				dateTime = dateTime.plusDays(i); // increment the days by
				d.setDate(dateTime.toDate());

				dateList.add(d); // add the DateSlice to the list !

			} // for loop

		} catch (Exception e) {
			e.printStackTrace();
		}

		return dateList;
	} // generateDateList

	public void printDateList(List<DateSlice> dList) {

		int i = 0;

		do {
			System.out.println(dList.get(i).toString());
			i++;
		} while (i < dList.size());

	} // printDateList

	public List<DateSlice> getDateSliceList(Calendar c) {

		String startDateIn = c.getLeaseStartDate().toString(); // "01/12/2019";
		String endDateIn = c.getLeaseEndDate().toString(); // "04/12/2019";

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		Date startDate = null;
		Date endDate = null;

		List<DateSlice> dateListIn = new ArrayList<DateSlice>();

		try {

			startDate = format.parse(startDateIn);
			endDate = format.parse(endDateIn);

			DateTime start_date = new DateTime(startDate);
			DateTime end_date = new DateTime(endDate);

			// we now generate an array of DateSlices to perform our date operations
			dateListIn = new ArrayList<DateSlice>(this.generateDateList(start_date, end_date));

			for (int i = 0; i < dateListIn.size(); i++) {
				dateListIn.get(i).setCalenderID(c.getCalendarID());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return dateListIn;

	} // getDateSliceList

	public KioskQuery mapKiosk(KioskQuery k, Kiosk kiosk, List<Rate> rateList, List<Zone> zoneList) {

		k.setKioskID(kiosk.getKioskID());
		k.setKioskNumber(kiosk.getKioskNumber());
		k.setGridLocationRow(kiosk.getGridLocationRow());
		k.setGridLocationColumn(kiosk.getGridLocationColumn());
		k.setIsVoid(kiosk.getIsVoid());
		k.setIsLocked(kiosk.getIsLocked());
		k.setZoneID(kiosk.getZoneID());

		for (int i = 0; i < zoneList.size(); i++) {

			if (k.getZoneID() == zoneList.get(i).getZoneID()) {
				k.setZone(zoneList.get(i));
			}

		} // zonelist

		for (int i = 0; i < rateList.size(); i++) {

			if (k.getZoneID() == rateList.get(i).getZoneID()) {
				// here we set the daily rate and calculate the total lease
				k.setDaily_rate(round(
						rateList.get(i).getRateMax() + (rateList.get(i).getRateCoeff() * k.getLease_duration()), 3));

				// we now have the daily rate, we can calculate the total lease
				k.setLease_total(round(k.getDaily_rate() * k.getLease_duration(), 2));
			}

		} // ratelist

		return k;
	} // mapKiosk

	public KioskQuery mapKiosk(KioskQuery k, Kiosk kiosk, Rate rate, Zone zone) {

		k.setKioskID(kiosk.getKioskID());
		k.setKioskNumber(kiosk.getKioskNumber());
		k.setGridLocationRow(kiosk.getGridLocationRow());
		k.setGridLocationColumn(kiosk.getGridLocationColumn());
		k.setIsVoid(kiosk.getIsVoid());
		k.setIsLocked(kiosk.getIsLocked());
		k.setZoneID(kiosk.getZoneID());
		k.setZone(zone);

		// set the pricing information
		k.setDaily_rate(round(rate.getRateMax() + (rate.getRateCoeff() * k.getLease_duration()), 3));
		k.setLease_total(round(k.getDaily_rate() * k.getLease_duration(), 2));

		return k;
	} // mapKiosk

	private static double round(double value, int places) {
		if (places < 0)
			throw new IllegalArgumentException();

		BigDecimal bd = new BigDecimal(value);
		bd = bd.setScale(places, RoundingMode.HALF_UP);
		return bd.doubleValue();
	} // round

	public int getDuration(String start_date_str, String end_date_str) {

		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

		Date startDate = null;
		Date endDate = null;
		int leaseDuration = 0;

		try {

			startDate = format.parse(start_date_str);
			endDate = format.parse(end_date_str);

			DateTime start_date = new DateTime(startDate);
			DateTime end_date = new DateTime(endDate);

			leaseDuration = Days.daysBetween(start_date, end_date).getDays() + 1;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return leaseDuration;

	} // getDuration

	public void processFlags(List<KioskQuery> k) {
		int bookingCount = 0;
		// method which checks the calendar for each kiosk and sets the availability
		// flag

		for (int i = 0; i < k.size(); i++) {
			bookingCount = 0;

			for (int j = 0; j < k.get(i).getDateList().size(); j++) {

				if (k.get(i).getDateList().get(j).getCalenderID() > 0) {
					bookingCount++;
				}

			} // inner for

			if (k.get(i).getIsLocked() < 1 && k.get(i).getIsVoid() < 1) {

				if (bookingCount == k.get(i).getDateList().size()) {
					// kiosk is fully booked
					k.get(i).setAvailability_color(Constants.not_avail_colour);
					k.get(i).setAvailability_status(Constants.not_avail);
					k.get(i).setNumber_days_available(0);
				}

				if (bookingCount > 0 && bookingCount < k.get(i).getDateList().size()) {
					k.get(i).setAvailability_color(Constants.part_avail_colour);
					k.get(i).setAvailability_status(Constants.part_avail);
					k.get(i).setNumber_days_available(k.get(i).getDateList().size() - bookingCount);
				}

				if (bookingCount == 0) {
					k.get(i).setAvailability_color(Constants.avail_colour);
					k.get(i).setAvailability_status(Constants.avail);
					k.get(i).setNumber_days_available(k.get(i).getDateList().size());

				}
			} // outer if

		} // outer for

	}

	public void setGridSize(List<KioskQuery> k, int row_max, int col_max) {

		// method which checks the calendar for each kiosk and sets the availability
		// flag

		for (int i = 0; i < k.size(); i++) {

			k.get(i).setLocation_grid_row_max(row_max);
			k.get(i).setLocation_grid_column_max(col_max);

		} // inner for

	} // setGridSize

	public void processFlags(KioskQuery k) {
		int bookingCount = 0;
		// method which checks the calendar for each kiosk and sets the availability
		// flag

		for (int j = 0; j < k.getDateList().size(); j++) {

			if (k.getDateList().get(j).getCalenderID() > 0) {
				bookingCount++;
			}

		} // inner for

		if (k.getIsLocked() < 1 && k.getIsVoid() < 1) {

			if (bookingCount == k.getDateList().size()) {
				// kiosk is fully booked
				k.setAvailability_color(Constants.not_avail_colour);
				k.setAvailability_status(Constants.not_avail);
				k.setNumber_days_available(0);
			}

			if (bookingCount > 0 && bookingCount < k.getDateList().size()) {
				k.setAvailability_color(Constants.part_avail_colour);
				k.setAvailability_status(Constants.part_avail);
				k.setNumber_days_available(k.getDateList().size() - bookingCount);
			}

			if (bookingCount == 0) {
				k.setAvailability_color(Constants.avail_colour);
				k.setAvailability_status(Constants.avail);
				k.setNumber_days_available(k.getDateList().size());

			}
		} // outer if

	}

	public void formatDates(List<KioskQuery> kioskQueryList) {

		for (int i = 0; i < kioskQueryList.size(); i++) {

			for (int j = 0; j < kioskQueryList.get(i).getDateList().size(); j++) {

				String dateString = new SimpleDateFormat("dd/MM/yyyy")
						.format(kioskQueryList.get(i).getDateList().get(j).getDate());

				kioskQueryList.get(i).getDateList().get(j).setKioskCalanderDATE(dateString);
				kioskQueryList.get(i).getDateList().get(j).setDate(null);

			} // inner for loop

		} // outer for loop

	}

	public void formatDates(KioskQuery k_query) {
		// TODO Auto-generated method stub
		for (int j = 0; j < k_query.getDateList().size(); j++) {

			String dateString = new SimpleDateFormat("dd/MM/yyyy").format(k_query.getDateList().get(j).getDate());
			k_query.getDateList().get(j).setKioskCalanderDATE(dateString);
			k_query.getDateList().get(j).setDate(null);

		} // inner for loop
	}

	public void mapCustomer(AddQuote q, Customer c) {
		// TODO Auto-generated method stub
		q.setCustomerID(c.getCustomerID());
		q.setFirstName(c.getFirstName());
		q.setLastName(c.getLastName());
		q.setEmailIDCus(c.getEmailIDCus());
		q.setAddress(c.getAddress());
		q.setEmirate(c.getEmirate());
		q.setMobileNumber(c.getMobileNumber());
		q.setPoBox(c.getPoBox());
		q.setOfficeNumber(c.getOfficeNumber());
		q.setTradeLicence(c.getTradeLicence());
		q.setTradeName(c.getTradeName());

	}

	public void mapCustomer(CreateCustomer customer, AddQuote q) {
		String s = "QUOTE ONLY - customer information pending booking";

		customer.setFirstName(q.getFirstName());
		customer.setLastName(q.getLastName());
		customer.setEmailIDCus(q.getEmailIDCus());

		customer.setAddress(s);
		customer.setEmirate(s);
		customer.setMobileNumber(s);
		customer.setPoBox(s);
		customer.setOfficeNumber(s);
		customer.setTradeLicence(0);
		customer.setTradeName(s);

		customer.setStaff_email(q.getStaff_email_id());

	}

	public void mapQuote(AddQuote q, CreateCustomer customer) {
		// TODO Auto-generated method stub

		q.setCustomerID(customer.getCustomerID());
		q.setEmailIDCus(customer.getEmailIDCus());
		q.setFirstName(customer.getFirstName());
		q.setLastName(customer.getLastName());
		q.setAddress(customer.getAddress());
		q.setPoBox(customer.getPoBox());
		q.setEmirate(customer.getEmirate());
		q.setMobileNumber(customer.getMobileNumber());
		q.setOfficeNumber(customer.getOfficeNumber());
		q.setTradeLicence(customer.getTradeLicence());
		q.setTradeName(customer.getTradeName());

	}

	public QuotationForm generateQuoteEmail(AddQuote q, User staff_details) {
		QuotationForm qForm = new QuotationForm();
		qForm.loadValues(q, staff_details);
		return qForm;
		// TODO Auto-generated method stub

	}

	public List<GridBuilder> scaleBackJSON(List<KioskQuery> k, List<GridBuilder> gridBuilder) {
		// TODO Auto-generated method stub
		GridBuilder g = new GridBuilder();

		for (int i = 0; i < k.size(); i++) {

			g.setLocationName(k.get(i).getLocation_name());
			g.setKioskID(k.get(i).getKioskID());
			g.setKioskNumber(k.get(i).getKioskNumber());
			g.setZoneNumber(k.get(i).getZone().getZoneNumber());

			g.setAvailability_color(k.get(i).getAvailability_color());
			g.setAvailability_status(k.get(i).getAvailability_status());

			g.setGridLocationRow(k.get(i).getGridLocationRow());
			g.setGridLocationColumn(k.get(i).getGridLocationColumn());

			g.setLocation_grid_row_max(k.get(i).getLocation_grid_row_max());
			g.setLocation_grid_column_max(k.get(i).getLocation_grid_column_max());

			g.setLease_total(k.get(i).getLease_total());

			gridBuilder.add(g);

		}

		return gridBuilder;

	}

	public void slimDownJSON(KioskQuery k_query) {
		// TODO Auto-generated method stub
		k_query.setZoneID(0);
		k_query.getZone().setZoneID(0);
		k_query.getZone().setLocationID(0);
		k_query.setNumber_days_available(0);
		
	}

}