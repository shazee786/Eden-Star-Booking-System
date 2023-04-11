package com.edenstar.model.dash;

import com.edenstar.model.Product;

public class CreateProduct extends Product {
	
	private String staff_email;

	public CreateProduct() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CreateProduct(int productID, int companyID, String description, String origin, String priceRange) {
		super(productID, companyID, description, origin, priceRange);
		// TODO Auto-generated constructor stub
	}

	public String getStaff_email() {
		return staff_email;
	}

	public void setStaff_email(String staff_email) {
		this.staff_email = staff_email;
	}

	@Override
	public String toString() {
		return "CreateProduct [staff_email=" + staff_email + "]";
	}
	
	

} // CreateProduct
