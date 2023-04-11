package com.eden.api.service.validation;

import com.edenstar.model.dash.CreateProduct;

public class ProductValidation {
	
	public boolean fieldsAreEmpty(CreateProduct p) {

		// check to make sure that the mandatory parameters are not empty
		if (p.getDescription().contentEquals("") || p.getOrigin().contentEquals("")
				|| p.getPriceRange().contentEquals("") || p.getStaff_email().contentEquals("") ||
				p.getCompanyID() < 1) {

			return true;

		} // if

		return false;

	} // fieldsAreEmpty
	
	public boolean isNull(CreateProduct p) {
		// make sure that none of the mandatory fields are null

			if (p.getDescription() == null)
				return true;
			if (p.getOrigin() == null)
				return true;
			if (p.getStaff_email() == null)
				return true;
			if(p.getPriceRange() == null)
				return true;


		return false;

	} // isNull


} // ProductValidation
