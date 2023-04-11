package com.edenstar.model.dash;

import com.edenstar.model.User;

public class GetUser extends User {

	private String staff_email;
	private int staff_employee_id;

	public GetUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GetUser(int employeeID, String emailID, String firstName, String lastName, String address, String dob,
			String phoneNumber, String password, String passwordHint, int isblocked, String profileUrl,
			String userLevel) {
		super(employeeID, emailID, firstName, lastName, address, dob, phoneNumber, password, passwordHint, isblocked,
				profileUrl, userLevel);
		// TODO Auto-generated constructor stub
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

	@Override
	public String toString() {
		return "GetUser [staff_email=" + staff_email + ", staff_employee_id=" + staff_employee_id + "]";
	}

} // GetUser
