package com.edenstar.model;

import java.util.Date;

public class Lease {

	private int leaseID;
	private int bookingID;
	private String comments;
	private Date dateOfLease;
	private int flagForManager;
	private int isApproved;
	private byte[] leaseAgreementPdf;
	private int retainDeposit;
	private byte[] thankYouLetterPdf;

	public Lease() {
	}

	public Lease(int leaseID, int bookingID, String comments, Date dateOfLease, int flagForManager, int isApproved,
			byte[] leaseAgreementPdf, int retainDeposit, byte[] thankYouLetterPdf) {
		super();
		this.leaseID = leaseID;
		this.bookingID = bookingID;
		this.comments = comments;
		this.dateOfLease = dateOfLease;
		this.flagForManager = flagForManager;
		this.isApproved = isApproved;
		this.leaseAgreementPdf = leaseAgreementPdf;
		this.retainDeposit = retainDeposit;
		this.thankYouLetterPdf = thankYouLetterPdf;
	}

	public int getLeaseID() {
		return leaseID;
	}

	public void setLeaseID(int leaseID) {
		this.leaseID = leaseID;
	}

	public int getBookingID() {
		return bookingID;
	}

	public void setBookingID(int bookingID) {
		this.bookingID = bookingID;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Date getDateOfLease() {
		return dateOfLease;
	}

	public void setDateOfLease(Date dateOfLease) {
		this.dateOfLease = dateOfLease;
	}

	public int getFlagForManager() {
		return flagForManager;
	}

	public void setFlagForManager(int flagForManager) {
		this.flagForManager = flagForManager;
	}

	public int getIsApproved() {
		return isApproved;
	}

	public void setIsApproved(int isApproved) {
		this.isApproved = isApproved;
	}

	public byte[] getLeaseAgreementPdf() {
		return leaseAgreementPdf;
	}

	public void setLeaseAgreementPdf(byte[] leaseAgreementPdf) {
		this.leaseAgreementPdf = leaseAgreementPdf;
	}

	public int getRetainDeposit() {
		return retainDeposit;
	}

	public void setRetainDeposit(int retainDeposit) {
		this.retainDeposit = retainDeposit;
	}

	public byte[] getThankYouLetterPdf() {
		return thankYouLetterPdf;
	}

	public void setThankYouLetterPdf(byte[] thankYouLetterPdf) {
		this.thankYouLetterPdf = thankYouLetterPdf;
	}

}