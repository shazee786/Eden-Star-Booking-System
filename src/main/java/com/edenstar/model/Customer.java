package com.edenstar.model;


public class Customer {

	private int customerID;
	private String emailIDCus;
	private String firstName;
	private String lastName;
	private String address;
	private String emirate;
	private String poBox;
	private String mobileNumber;
	private String officeNumber;
	private int tradeLicence;
	private String tradeName;
	
	public Customer() {
		super();
	}

	public Customer(int customerID, String emailIDCus, String firstName, String lastName, String address,
			String emirate, String poBox, String mobileNumber, String officeNumber, int tradeLicence,
			String tradeName) {
		super();
		this.customerID = customerID;
		this.emailIDCus = emailIDCus;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.emirate = emirate;
		this.poBox = poBox;
		this.mobileNumber = mobileNumber;
		this.officeNumber = officeNumber;
		this.tradeLicence = tradeLicence;
		this.tradeName = tradeName;
	}

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public String getEmailIDCus() {
		return emailIDCus;
	}

	public void setEmailIDCus(String emailIDCus) {
		this.emailIDCus = emailIDCus;
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

	public String getEmirate() {
		return emirate;
	}

	public void setEmirate(String emirate) {
		this.emirate = emirate;
	}

	public String getPoBox() {
		return poBox;
	}

	public void setPoBox(String poBox) {
		this.poBox = poBox;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getOfficeNumber() {
		return officeNumber;
	}

	public void setOfficeNumber(String officeNumber) {
		this.officeNumber = officeNumber;
	}

	public int getTradeLicence() {
		return tradeLicence;
	}

	public void setTradeLicence(int tradeLicence) {
		this.tradeLicence = tradeLicence;
	}

	public String getTradeName() {
		return tradeName;
	}

	public void setTradeName(String tradeName) {
		this.tradeName = tradeName;
	}

	@Override
	public String toString() {
		return "Customer [customerID=" + customerID + ", emailIDCus=" + emailIDCus + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", address=" + address + ", emirate=" + emirate + ", poBox=" + poBox
				+ ", mobileNumber=" + mobileNumber + ", officeNumber=" + officeNumber + ", tradeLicence=" + tradeLicence
				+ ", tradeName=" + tradeName + "]";
	}
	
} 