package com.edenstar.model.dash;

import com.edenstar.model.Company;

public class GetCompany extends Company {
	
	private String staff_email;

	public GetCompany() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GetCompany(int companyID, int customerID, String companyName, byte[] companyLogo) {
		super(companyID, customerID, companyName, companyLogo);
		// TODO Auto-generated constructor stub
	}

	public String getStaff_email() {
		return staff_email;
	}

	public void setStaff_email(String staff_email) {
		this.staff_email = staff_email;
	}
	

}
