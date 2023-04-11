package com.edenstar.model;

import java.util.Date;

public class Quote {

	private int quote_ID;
	private String quoteRef;
	private int customer_ID;
	private Date date_of_quote;
	private int employee_ID;
	private int kiosk_ID;

	private int expiry_duration_days;
	private int isExpired;

	private int lease_duration_days;
	private Date lease_end_date;
	private Date lease_start_date;
	private double lease_total;
	private double rate;

	private byte[] quotationPdf;

	public Quote() {
		super();
	}

	public Quote(int quote_ID, String quoteRef, int customer_ID, Date date_of_quote, int employee_ID, int kiosk_ID,
			int expiry_duration_days, int isExpired, int lease_duration_days, Date lease_end_date,
			Date lease_start_date, double lease_total, double rate, byte[] quotationPdf) {
		super();
		this.quote_ID = quote_ID;
		this.quoteRef = quoteRef;
		this.customer_ID = customer_ID;
		this.date_of_quote = date_of_quote;
		this.employee_ID = employee_ID;
		this.kiosk_ID = kiosk_ID;
		this.expiry_duration_days = expiry_duration_days;
		this.isExpired = isExpired;
		this.lease_duration_days = lease_duration_days;
		this.lease_end_date = lease_end_date;
		this.lease_start_date = lease_start_date;
		this.lease_total = lease_total;
		this.rate = rate;
		this.quotationPdf = quotationPdf;
	}

	public int getQuote_ID() {
		return quote_ID;
	}

	public void setQuote_ID(int quote_ID) {
		this.quote_ID = quote_ID;
	}

	public String getQuoteRef() {
		return quoteRef;
	}

	public void setQuoteRef(String quoteRef) {
		this.quoteRef = quoteRef;
	}

	public int getCustomer_ID() {
		return customer_ID;
	}

	public void setCustomer_ID(int customer_ID) {
		this.customer_ID = customer_ID;
	}

	public Date getDate_of_quote() {
		return date_of_quote;
	}

	public void setDate_of_quote(Date date_of_quote) {
		this.date_of_quote = date_of_quote;
	}

	public int getEmployee_ID() {
		return employee_ID;
	}

	public void setEmployee_ID(int employee_ID) {
		this.employee_ID = employee_ID;
	}

	public int getKiosk_ID() {
		return kiosk_ID;
	}

	public void setKiosk_ID(int kiosk_ID) {
		this.kiosk_ID = kiosk_ID;
	}

	public int getExpiry_duration_days() {
		return expiry_duration_days;
	}

	public void setExpiry_duration_days(int expiry_duration_days) {
		this.expiry_duration_days = expiry_duration_days;
	}

	public int getIsExpired() {
		return isExpired;
	}

	public void setIsExpired(int isExpired) {
		this.isExpired = isExpired;
	}

	public int getLease_duration_days() {
		return lease_duration_days;
	}

	public void setLease_duration_days(int lease_duration_days) {
		this.lease_duration_days = lease_duration_days;
	}

	public Date getLease_end_date() {
		return lease_end_date;
	}

	public void setLease_end_date(Date lease_end_date) {
		this.lease_end_date = lease_end_date;
	}

	public Date getLease_start_date() {
		return lease_start_date;
	}

	public void setLease_start_date(Date lease_start_date) {
		this.lease_start_date = lease_start_date;
	}

	public double getLease_total() {
		return lease_total;
	}

	public void setLease_total(double lease_total) {
		this.lease_total = lease_total;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public byte[] getQuotationPdf() {
		return quotationPdf;
	}

	public void setQuotationPdf(byte[] quotationPdf) {
		this.quotationPdf = quotationPdf;
	}

	@Override
	public String toString() {
		return "Quote [quote_ID=" + quote_ID + ", quoteRef=" + quoteRef + ", customer_ID=" + customer_ID
				+ ", date_of_quote=" + date_of_quote + ", employee_ID=" + employee_ID + ", kiosk_ID=" + kiosk_ID
				+ ", expiry_duration_days=" + expiry_duration_days + ", isExpired=" + isExpired
				+ ", lease_duration_days=" + lease_duration_days + ", lease_end_date=" + lease_end_date
				+ ", lease_start_date=" + lease_start_date + ", lease_total=" + lease_total + ", rate=" + rate + "]";
	}

}