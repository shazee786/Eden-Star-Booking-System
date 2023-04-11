package com.edenstar.model;

public class User {

	private int employeeID;
	private String emailID;
	private String firstName;
	private String lastName;
	private String address;
	private String dob;
	private String phoneNumber;
	private String password;
	private String passwordHint;
	private int isblocked;
	private String profileUrl;
	private String userLevel;

	public User(int employeeID, String emailID, String firstName, String lastName, String address, String dob,
			String phoneNumber, String password, String passwordHint, int isblocked, String profileUrl,
			String userLevel) {
		super();
		this.employeeID = employeeID;
		this.emailID = emailID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.dob = dob;
		this.phoneNumber = phoneNumber;
		this.password = password;
		this.passwordHint = passwordHint;
		this.isblocked = isblocked;
		this.profileUrl = profileUrl;
		this.userLevel = userLevel;
	}

	public User() {
		super();
	}

	public int getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}

	public String getEmailID() {
		return emailID;
	}

	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordHint() {
		return passwordHint;
	}

	public void setPasswordHint(String passwordHint) {
		this.passwordHint = passwordHint;
	}

	public int getIsblocked() {
		return isblocked;
	}

	public void setIsblocked(int isblocked) {
		this.isblocked = isblocked;
	}

	public String getProfileUrl() {
		return profileUrl;
	}

	public void setProfileUrl(String profileUrl) {
		this.profileUrl = profileUrl;
	}

	public String getUserLevel() {
		return userLevel;
	}

	public void setUserLevel(String userLevel) {
		this.userLevel = userLevel;
	}

	@Override
	public String toString() {
		return "User [employeeID=" + employeeID + ", emailID=" + emailID + ", firstName=" + firstName + ", lastName="
				+ lastName + ", address=" + address + ", dob=" + dob + ", phoneNumber=" + phoneNumber + ", password="
				+ password + ", passwordHint=" + passwordHint + ", isblocked=" + isblocked + ", profileUrl="
				+ profileUrl + ", userLevel=" + userLevel + "]";
	}

}