package com.eden.api.dao;

import java.util.List;

import com.edenstar.model.Calendar;
import com.edenstar.model.booking.AddQuote;

public interface BookingDAO {

	// returns the booking calendar for a given kiosk
	List<Calendar> getBookingForKiosk(int kioskID) throws Exception;

	// add a quote to the database
	int addQuote(AddQuote q) throws Exception;

}
