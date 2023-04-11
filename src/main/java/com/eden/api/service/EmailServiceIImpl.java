package com.eden.api.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.edenstar.model.User;
import com.eden.api.util.Constants;

@Component
public class EmailServiceIImpl implements EmailService {

	private Properties props = new Properties();

	@Autowired
	public EmailServiceIImpl() {
		props.put("mail.smtp.host", Constants.host);
		props.put("mail.smtp.socketFactory.port", Constants.port);
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", Constants.port);

		// additional switches
		props.put("mail.smtp.debug", "true");
		props.put("mail.smtp.socketFactory.fallback", "false");
		props.put("mail.smtp.starttls.enable", "true");
		
		// secure switches
		props.setProperty("mail.smtps.host", Constants.host);
		props.put("mail.smtps.port", 587);
		props.put("mail.smtps.auth", "true");
	}

	public void sendEmail(String emailTo, String emailTitle, String messageBody) throws Exception {

		try {
			// get session
			Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(Constants.emailFrom, Constants.password);
				}
			});

			// compose message

			// try {
			MimeMessage message = new MimeMessage(session);
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailTo));
			message.setSubject(emailTitle);
			message.setText(messageBody);

			// Transport.send(message);
			Transport transport = session.getTransport("smtps");
			transport.connect(Constants.host, Integer.valueOf(Constants.port), Constants.emailFrom, Constants.password);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}

	} // sendEmail

	public void sendHTMLEmail(String emailTo, String emailTitle, String messageBody) throws Exception {

		MimeMultipart multipart = new MimeMultipart();

		String htmlContent = messageBody;
		// add HTML content here
		MimeBodyPart messageBodyPart = new MimeBodyPart();
		// HTML Content
		messageBodyPart.setContent(htmlContent, "text/html;charset=UTF-8");

		// add it
		multipart.addBodyPart(messageBodyPart);

//		ClassLoader cldr = this.getClass().getClassLoader();
//		URL imageUrl = cldr.getResource("/com/edenstar/model/booking/images/eden_star_logo.png");
		// we need to reference the company logo and add it as a header
		// URL imageUrl =
		// this.getClass().getResource("/com/edenstar/model/booking/images/eden_star_logo.png");
		// URL imageURL =
		// this.getClass().getClassLoader().getResource("/images/eden_star_logo.png");
		// System.out.println("image url = " + imageURL.getPath());

		// ClassPathResource imgFile = new ClassPathResource("eden_star_logo.png");
		// imgFile.getURL().getPath();
		// URL iconUrl = this.getClass().getResource("/eden_star_logo.png");
		// URLDataSource source = new
		// URLDataSource(this.getClass().getResource("eden_star_logo.png"));
		// System.out.println("image url = " + source.getURL().getPath());

		// second part (the image)
		messageBodyPart = new MimeBodyPart();
		// new MimeBodyPart().attachFile(new
		// File(this.getClass().getClassLoader().getResource("resources/Cloudy_Day.png").toURI());

		messageBodyPart.setHeader("Content-ID", "edenlogoimage");
		messageBodyPart.setDisposition(MimeBodyPart.INLINE);

		try {
			// attach image
			InputStream inputStream = getClass().getResourceAsStream("/eden_star_logo.png");

			byte[] buffer = new byte[inputStream.available()];
			inputStream.read(buffer);

			File targetFile = new File("edenstarlogo.png");
			OutputStream outStream = new FileOutputStream(targetFile);
			outStream.write(buffer);

			//ClassPathResource cl = new ClassPathResource("edenstarlogo.png");
			//URL url = cl.getURL();
			System.out.println("targetFile path = " + targetFile.getPath());
			System.out.println("targetFile absolute path = " + targetFile.getAbsolutePath());
			
			// messageBodyPart.attachFile(is);
			//messageBodyPart.attachFile(targetFile.getPath());
			messageBodyPart.attachFile(targetFile.getAbsolutePath());

			// add it
			multipart.addBodyPart(messageBodyPart);
			
			IOUtils.closeQuietly(inputStream);
			IOUtils.closeQuietly(outStream);

		} catch (IOException io) {
			throw new RuntimeException(io);
		}

		try {
			// get session
			Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(Constants.emailFrom, Constants.password);
				}
			});

			// compose message

			// try {
			MimeMessage message = new MimeMessage(session);
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailTo));
			message.setSubject(emailTitle);
			message.setContent(multipart);
			;

			// Transport.send(message);
			Transport transport = session.getTransport("smtps");
			transport.connect(Constants.host, Integer.valueOf(Constants.port), Constants.emailFrom, Constants.password);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}

	} // sendEmail

	@Override
	public boolean emailLostPassword(User u) throws Exception {

		String msg = "Dear " + u.getFirstName() + " " + u.getLastName() + ",\n\n"
				+ "please find your email and password for the Eden Star Booking System listed below. \n\n"
				+ "Username : " + u.getEmailID() + "\n" + "Password : " + u.getPassword() + "\n\n" + "Best Wishes \n"
				+ "Eden Star Admin Team";

		sendEmail(u.getEmailID(), "Eden Star Admin", msg);
		return true;
	}

	@Override
	public boolean emailQuoteToCustomer(String emailIDCus, String messageBody) throws Exception {

		sendHTMLEmail(emailIDCus, "Eden Star - Your Quotation", messageBody);
		return true;
	}

}
