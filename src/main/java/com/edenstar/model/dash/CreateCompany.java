package com.edenstar.model.dash;

import com.edenstar.model.Company;

public class CreateCompany extends Company {
	
	private String staff_email;
	private String email_id_cus;
	
	public CreateCompany() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CreateCompany(int companyID, int customerID, String companyName, byte[] companyLogo) {
		super(companyID, customerID, companyName, companyLogo);
		// TODO Auto-generated constructor stub
	}
	public String getStaff_email() {
		return staff_email;
	}
	public void setStaff_email(String staff_email) {
		this.staff_email = staff_email;
	}
	public String getEmail_id_cus() {
		return email_id_cus;
	}
	public void setEmail_id_cus(String email_id_cus) {
		this.email_id_cus = email_id_cus;
	}


} // CreateCompany
