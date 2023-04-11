package com.edenstar.model;

import java.util.Date;

public class Payment {

	private int paymentId;
	private int accountID;
	private double amountDue;
	private Date dateDue;
	private Date receivedDate;
		
	public Payment() {
		super();
	}

	public Payment(int paymentId, int accountID, double amountDue, Date dateDue, Date receivedDate) {
		super();
		this.paymentId = paymentId;
		this.accountID = accountID;
		this.amountDue = amountDue;
		this.dateDue = dateDue;
		this.receivedDate = receivedDate;
	}

	public int getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}

	public int getAccountID() {
		return accountID;
	}

	public void setAccountID(int accountID) {
		this.accountID = accountID;
	}

	public double getAmountDue() {
		return amountDue;
	}

	public void setAmountDue(double amountDue) {
		this.amountDue = amountDue;
	}

	public Date getDateDue() {
		return dateDue;
	}

	public void setDateDue(Date dateDue) {
		this.dateDue = dateDue;
	}

	public Date getReceivedDate() {
		return receivedDate;
	}

	public void setReceivedDate(Date receivedDate) {
		this.receivedDate = receivedDate;
	}

	@Override
	public String toString() {
		return "Payment [paymentId=" + paymentId + ", accountID=" + accountID + ", amountDue=" + amountDue
				+ ", dateDue=" + dateDue + ", receivedDate=" + receivedDate + "]";
	}

	


}