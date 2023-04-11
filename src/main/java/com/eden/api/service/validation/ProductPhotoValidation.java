package com.eden.api.service.validation;

import com.edenstar.model.dash.CreateProductPhoto;

public class ProductPhotoValidation {

	public boolean fieldsAreEmpty(CreateProductPhoto p) {

		// check to make sure that the mandatory parameters are not empty
		if (p.getStaff_email().contentEquals("") || p.getDescription().contentEquals("") || p.getProductID() < 1) {

			return true;

		} // if

		return false;

	} // fieldsAreEmpty

	public boolean isNull(CreateProductPhoto p) {
		// make sure that none of the mandatory fields are null

		if ((p.getProductPhoto() == null) || (p.getDescription() == null) || p.getProductID() < 1
				|| p.getStaff_email() == null) {

		}
		return false;

	} // isNull

} // ProductPhotoValidation
