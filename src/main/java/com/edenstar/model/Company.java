package com.edenstar.model;

import java.util.Arrays;

import javax.persistence.Embeddable;

@Embeddable
public class Company {

	private int companyID;
	private int customerID;
	private String companyName;
	private byte[] companyLogo;

	public Company() {
		super();
	}

	public Company(int companyID, int customerID, String companyName, byte[] companyLogo) {
		super();
		this.companyID = companyID;
		this.customerID = customerID;
		this.companyName = companyName;
		this.companyLogo = companyLogo;
	}

	public int getCompanyID() {
		return companyID;
	}

	public void setCompanyID(int companyID) {
		this.companyID = companyID;
	}

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public byte[] getCompanyLogo() {
		return companyLogo;
	}

	public void setCompanyLogo(byte[] companyLogo) {
		this.companyLogo = companyLogo;
	}

	@Override
	public String toString() {
		return "Company [companyID=" + companyID + ", customerID=" + customerID + ", companyName=" + companyName
				+ ", companyLogo=" + Arrays.toString(companyLogo) + "]";
	}

}