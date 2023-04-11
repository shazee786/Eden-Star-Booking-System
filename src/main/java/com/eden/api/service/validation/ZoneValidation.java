package com.eden.api.service.validation;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.edenstar.model.dash.CreateZone;

public class ZoneValidation {
	
	public boolean fieldsAreEmpty(CreateZone z) {

		// check to make sure that the mandatory parameters are not empty
		if (z.getStaff_email_id().contentEquals("") || z.getLocationID() == 0
				|| z.getRate() == null) {

			return true;

		} // if

		return false;

	} // fieldsAreEmpty

	public boolean isNull(CreateZone z) {
		// make sure that none of the mandatory fields are null


		if (z.getStaff_email_id() == null)
			return true;
		if (z.getRate() == null)
			return true;
		if (z.getLocationID() == 0)
			return true;

		return false;

	} // isNull
	
	
	public boolean validateRates(CreateZone z) {
		// makes sure that the rate passed for the zone is within normal values
		boolean isValid =  true;
		double rateCoeff = 0.00d;
		


			if (z.getRate().getRateMax() < 0.0
					|| z.getRate().getRateMin() > z.getRate().getRateMax()
					|| z.getRate().getDiscountDurationDays() < 1)
				isValid = false;

		// if all the values in the array are normal (i.e. isValid = true)
		// we should calculate the coefficient 
		
		if (isValid) {

				double coeff = (z.getRate().getRateMin() - z.getRate().getRateMax()) 
						/ z.getRate().getDiscountDurationDays();
			
				// round off to 2 decimal places
				rateCoeff = round(coeff, 2);
				
				// set the coefficient 
				z.getRate().setRateCoeff(rateCoeff);

		} // set the rate coefficient
		
		return isValid;
		
	} // validateRates
	
	private static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    BigDecimal bd = new BigDecimal(value);
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	} // round

} // Zone validation
