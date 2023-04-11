package com.edenstar.model;

import java.util.Arrays;
import java.util.Date;

public class Account {

	private int accountID;
	private int leaseID;
	private double depositAmount;
	private Date depositClearedDate;
	private int depositReceived;

	private double netLease;
	private String paymentMethod;
	private double paymentsRemaining;
	private double totalPaymentsReceived;

	private byte[] securityDepositScanPdf;
	private byte[] depositReceiptScan;

	public Account() {
		super();
	}

	public Account(int accountID, int contractID, double depositAmount, Date depositClearedDate, int depositReceived,
			double netLease, String paymentMethod, double paymentsRemaining, double totalPaymentsReceived,
			byte[] securityDepositScanPdf, byte[] depositReceiptScan) {
		super();
		this.accountID = accountID;
		this.leaseID = contractID;
		this.depositAmount = depositAmount;
		this.depositClearedDate = depositClearedDate;
		this.depositReceived = depositReceived;
		this.netLease = netLease;
		this.paymentMethod = paymentMethod;
		this.paymentsRemaining = paymentsRemaining;
		this.totalPaymentsReceived = totalPaymentsReceived;
		this.securityDepositScanPdf = securityDepositScanPdf;
		this.depositReceiptScan = depositReceiptScan;
	}

	public int getAccountID() {
		return accountID;
	}

	public void setAccountID(int accountID) {
		this.accountID = accountID;
	}

	public int getContractID() {
		return leaseID;
	}

	public void setContractID(int contractID) {
		this.leaseID = contractID;
	}

	public double getDepositAmount() {
		return depositAmount;
	}

	public void setDepositAmount(double depositAmount) {
		this.depositAmount = depositAmount;
	}

	public Date getDepositClearedDate() {
		return depositClearedDate;
	}

	public void setDepositClearedDate(Date depositClearedDate) {
		this.depositClearedDate = depositClearedDate;
	}

	public int getDepositReceived() {
		return depositReceived;
	}

	public void setDepositReceived(int depositReceived) {
		this.depositReceived = depositReceived;
	}

	public double getNetLease() {
		return netLease;
	}

	public void setNetLease(double netLease) {
		this.netLease = netLease;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public double getPaymentsRemaining() {
		return paymentsRemaining;
	}

	public void setPaymentsRemaining(double paymentsRemaining) {
		this.paymentsRemaining = paymentsRemaining;
	}

	public double getTotalPaymentsReceived() {
		return totalPaymentsReceived;
	}

	public void setTotalPaymentsReceived(double totalPaymentsReceived) {
		this.totalPaymentsReceived = totalPaymentsReceived;
	}

	public byte[] getSecurityDepositScanPdf() {
		return securityDepositScanPdf;
	}

	public void setSecurityDepositScanPdf(byte[] securityDepositScanPdf) {
		this.securityDepositScanPdf = securityDepositScanPdf;
	}

	public byte[] getDepositReceiptScan() {
		return depositReceiptScan;
	}

	public void setDepositReceiptScan(byte[] depositReceiptScan) {
		this.depositReceiptScan = depositReceiptScan;
	}

	@Override
	public String toString() {
		return "Account [accountID=" + accountID + ", contractID=" + leaseID + ", depositAmount=" + depositAmount
				+ ", depositClearedDate=" + depositClearedDate + ", depositReceived=" + depositReceived + ", netLease="
				+ netLease + ", paymentMethod=" + paymentMethod + ", paymentsRemaining=" + paymentsRemaining
				+ ", totalPaymentsReceived=" + totalPaymentsReceived + ", securityDepositScanPdf="
				+ Arrays.toString(securityDepositScanPdf) + ", depositReceiptScan="
				+ Arrays.toString(depositReceiptScan) + "]";
	}

}