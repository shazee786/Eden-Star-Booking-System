package com.edenstar.model.dash;

import com.edenstar.model.User;

public class ChangePassword extends User {

	private String newPassword;

	public ChangePassword(int employeeID, String emailID, String firstName, String lastName, String address, String dob,
			String phoneNumber, String password, String passwordHint, int isblocked, String profileUrl,
			String userLevel, String newPassword) {
		super(employeeID, emailID, firstName, lastName, address, dob, phoneNumber, password, passwordHint, isblocked,
				profileUrl, userLevel);
		this.newPassword = newPassword;
	}

	public ChangePassword() {
		super();
	}
	

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	@Override
	public String toString() {
		return "ChangePassword [newPassword=" + newPassword + "]";
	}
	
	

}
