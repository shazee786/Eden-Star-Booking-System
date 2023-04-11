package com.eden.api.service;

import java.util.List;

import com.edenstar.model.Calendar;
import com.edenstar.model.booking.AddQuote;

public interface BookingService {

	
	// returns the booking calendar for the given kiosk id
	List<Calendar> getBookingsForKiosk(int kioskID) throws Exception;

	// add a quote to the database
	int addQuote(AddQuote q) throws Exception;

	// email a quote to the customer
	boolean emailQuoteToCustomer(String emailIDCus, String messageBody) throws Exception;

}
