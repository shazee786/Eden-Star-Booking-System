package com.edenstar.model.dash;

import javax.persistence.Embedded;

import com.edenstar.model.Company;
import com.edenstar.model.Customer;

public class CreateCustomer extends Customer {

	private String staff_email;
	private int staff_employee_id;
	@Embedded
	private Company company = new Company();

	public CreateCustomer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CreateCustomer(int customerID, String emailIDCus, String firstName, String lastName, String address,
			String emirate, String poBox, String mobileNumber, String officeNumber, int tradeLicence,
			String tradeName) {
		super(customerID, emailIDCus, firstName, lastName, address, emirate, poBox, mobileNumber, officeNumber,
				tradeLicence, tradeName);
		// TODO Auto-generated constructor stub
	}

	public CreateCustomer(String staff_email, int staff_employee_id, Company company) {
		super();
		this.staff_email = staff_email;
		this.staff_employee_id = staff_employee_id;
		this.company = company;
	}

	public String getStaff_email() {
		return staff_email;
	}

	public void setStaff_email(String staff_email) {
		this.staff_email = staff_email;
	}

	public int getStaff_employee_id() {
		return staff_employee_id;
	}

	public void setStaff_employee_id(int staff_employee_id) {
		this.staff_employee_id = staff_employee_id;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	@Override
	public String toString() {
		return "CreateCustomer [staff_email=" + staff_email + ", staff_employee_id=" + staff_employee_id + ", company="
				+ company + "]";
	}

} // CreateCustomer
