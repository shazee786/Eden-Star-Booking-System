package com.eden.api.service;

import com.edenstar.model.User;

public interface EmailService {

	// sends an email to the user with their lost password
	boolean emailLostPassword(User u) throws Exception;

	// email a quote to the customer
	boolean emailQuoteToCustomer(String emailIDCus, String messageBody) throws Exception;

}
