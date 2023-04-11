package com.eden.api.service.validation;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.edenstar.model.booking.AddQuote;
import com.edenstar.model.booking.QueryLocation;

public class LocationQueryValidation {

	public boolean isNull(QueryLocation l) {
		// make sure that none of the mandatory fields are null

		if (l.getLocation_id() == 0)
			return true;
		if (l.getStaff_email_id() == null)
			return true;
		if (l.getStartDate() == null)
			return true;
		if (l.getEndDate() == null)
			return true;

		return false;

	} // isNull
	
	public boolean isNull(AddQuote l) {
		// make sure that none of the mandatory fields are null

		if (l.getKiosk_id() == 0)
			return true;
		if (l.getStaff_email_id() == null)
			return true;
		if (l.getEmailIDCus() == null)
			return true;
		if (l.getFirstName() == null)
			return true;
		if (l.getLastName() == null)
			return true;
		if (l.getStartDate() == null)
			return true;
		if (l.getEndDate() == null)
			return true;

		return false;

	} // isNull

	public boolean fieldsAreEmpty(QueryLocation l) {

		// check to make sure that the mandatory parameters are not empty
		if (l.getStaff_email_id().contentEquals("") || l.getLocation_id() < 1 || l.getStaff_email_id().isEmpty()
				|| l.getStartDate().isEmpty() || l.getEndDate().isEmpty()) {

			return true;

		}

		return false;

	} // fieldsAreEmpty
	
	private boolean isValidDate(String dateToValidate){
	        String pattern = "dd/MM/yyyy";

	        try {
	            DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern);
	            fmt.parseDateTime(dateToValidate);
	        } catch (Exception e) {
	            return false;
	        }
	        return true;
	 }
	
	public String checkDates(String startDateStr, String endDateStr) throws Exception {
		
	     String isValid = "";
	        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

	        Date startDate = null;
	        Date endDate = null;

	        try {

	            startDate = format.parse(startDateStr);
	            endDate = format.parse(endDateStr);

	            DateTime start_date = new DateTime(startDate);
	            DateTime end_date = new DateTime(endDate);

	            if (isValidDate(startDateStr) == false) {
	                isValid = isValid + "[startDate is not a valid date]";
	            }

	            if (isValidDate(endDateStr) == false) {
	                isValid = isValid + "[endDate is not a valid date]";
	            }

	            if (!isValid.equals("")) return isValid;

	            // start date cannot be after end date
	            if (start_date.compareTo(end_date) > 0) {
	                isValid = isValid + "[startDate cannot be after endDate]";
	            }

	            // start date cannot be before current date
	            if (start_date.isBeforeNow()) {
	                isValid = isValid + "[startDate cannot be today or before today's date]";
	            }
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	        }


	        if (isValid.equals("")) return null;

	        return isValid;
	} //checkDates

	public boolean fieldsAreEmpty(AddQuote l) {

		// check to make sure that the mandatory parameters are not empty
		if (l.getStaff_email_id().contentEquals("") || l.getKiosk_id() < 1 || l.getStaff_email_id().isEmpty()
				|| l.getStartDate().isEmpty() || l.getEndDate().isEmpty()) {

			return true;

		}

		return false;
	}

	public boolean isNull_kiosk(QueryLocation l) {
		// make sure that none of the mandatory fields are null

		if (l.getKiosk_id() == 0)
			return true;
		if (l.getStaff_email_id() == null)
			return true;
		if (l.getStartDate() == null)
			return true;
		if (l.getEndDate() == null)
			return true;

		return false;
	}

	public boolean fieldsAreEmpty_kiosk(QueryLocation l) {
		// check to make sure that the mandatory parameters are not empty
		if (l.getStaff_email_id().contentEquals("") || l.getKiosk_id() < 1 || l.getStaff_email_id().isEmpty()
				|| l.getStartDate().isEmpty() || l.getEndDate().isEmpty()) {

			return true;

		}

		return false;
	}

} // LocationQueryValidation
