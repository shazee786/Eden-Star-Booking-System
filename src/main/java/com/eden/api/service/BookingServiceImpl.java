package com.eden.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eden.api.dao.BookingDAO;
import com.edenstar.model.Calendar;
import com.edenstar.model.booking.AddQuote;

@Service("bookingService")
public class BookingServiceImpl implements BookingService {

	@Autowired
	private BookingDAO bookingDAO;
	
	@Autowired
	private EmailService emailService;

	@Override
	public List<Calendar> getBookingsForKiosk(int kioskID) throws Exception {
		// TODO Auto-generated method stub
		return bookingDAO.getBookingForKiosk(kioskID);
	}

	@Override
	public int addQuote(AddQuote q) throws Exception {
		// TODO Auto-generated method stub
		return bookingDAO.addQuote(q);
	}

	@Override
	public boolean emailQuoteToCustomer(String emailIDCus, String messageBody) throws Exception {
		// TODO Auto-generated method stub
		return emailService.emailQuoteToCustomer(emailIDCus, messageBody);
	}
	
	
}
