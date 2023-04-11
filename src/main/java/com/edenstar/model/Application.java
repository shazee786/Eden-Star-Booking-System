package com.edenstar.model;

import java.util.Date;

public class Application {

	private int applicationID;
	private int quoteID;
	private int managerID;

	private String comments;
	private Date dateOfApplication;
	private int isApproved;
	private byte reviewFlag;

	public Application() {
	}

	public Application(int applicationID, int quoteID, int managerID, String comments, Date dateOfApplication,
			int isApproved, byte reviewFlag) {
		super();
		this.applicationID = applicationID;
		this.quoteID = quoteID;
		this.managerID = managerID;
		this.comments = comments;
		this.dateOfApplication = dateOfApplication;
		this.isApproved = isApproved;
		this.reviewFlag = reviewFlag;
	}

	public int getApplicationID() {
		return applicationID;
	}

	public void setApplicationID(int applicationID) {
		this.applicationID = applicationID;
	}

	public int getQuoteID() {
		return quoteID;
	}

	public void setQuoteID(int quoteID) {
		this.quoteID = quoteID;
	}

	public int getManagerID() {
		return managerID;
	}

	public void setManagerID(int managerID) {
		this.managerID = managerID;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Date getDateOfApplication() {
		return dateOfApplication;
	}

	public void setDateOfApplication(Date dateOfApplication) {
		this.dateOfApplication = dateOfApplication;
	}

	public int getIsApproved() {
		return isApproved;
	}

	public void setIsApproved(int isApproved) {
		this.isApproved = isApproved;
	}

	public byte getReviewFlag() {
		return reviewFlag;
	}

	public void setReviewFlag(byte reviewFlag) {
		this.reviewFlag = reviewFlag;
	}

}