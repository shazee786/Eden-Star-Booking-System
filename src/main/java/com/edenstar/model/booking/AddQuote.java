package com.edenstar.model.booking;

import java.time.LocalDate;
import java.util.List;

import com.edenstar.model.Company;
import com.edenstar.model.Customer;
import com.fasterxml.jackson.annotation.JsonFormat;

public class AddQuote extends Customer {

	// quote information

	private int quote_id;
	private String staff_email_id;
	private int kiosk_id;
	private int employee_id;
	private String quoteRef;
	private int expiry_duration_days;

	private String companyName;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "en_GB")
	private LocalDate date_of_quote;
	private String startDate;
	private String endDate;
	private KioskQuery q_kiosk;
	private List<Company> companyList;

	private byte[] quotationPDF;

	public AddQuote() {
		super();

	}

	public AddQuote(int customerID, String emailIDCus, String firstName, String lastName, String address,
			String emirate, String poBox, String mobileNumber, String officeNumber, int tradeLicence,
			String tradeName) {
		super(customerID, emailIDCus, firstName, lastName, address, emirate, poBox, mobileNumber, officeNumber,
				tradeLicence, tradeName);
		// TODO Auto-generated constructor stub
	}

	public AddQuote(int quote_id, String staff_email_id, int kiosk_id, int employee_id, String quoteRef,
			int expiry_duration_days, String companyName, LocalDate date_of_quote, String startDate, String endDate,
			KioskQuery q_kiosk, List<Company> companyList, byte[] quotationPDF) {
		super();
		this.quote_id = quote_id;
		this.staff_email_id = staff_email_id;
		this.kiosk_id = kiosk_id;
		this.employee_id = employee_id;
		this.quoteRef = quoteRef;
		this.expiry_duration_days = expiry_duration_days;
		this.companyName = companyName;
		this.date_of_quote = date_of_quote;
		this.startDate = startDate;
		this.endDate = endDate;
		this.q_kiosk = q_kiosk;
		this.companyList = companyList;
		this.quotationPDF = quotationPDF;
	}

	public int getQuote_id() {
		return quote_id;
	}

	public void setQuote_id(int quote_id) {
		this.quote_id = quote_id;
	}

	public String getStaff_email_id() {
		return staff_email_id;
	}

	public void setStaff_email_id(String staff_email_id) {
		this.staff_email_id = staff_email_id;
	}

	public int getKiosk_id() {
		return kiosk_id;
	}

	public void setKiosk_id(int kiosk_id) {
		this.kiosk_id = kiosk_id;
	}

	public int getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(int employee_id) {
		this.employee_id = employee_id;
	}

	public String getQuoteRef() {
		return quoteRef;
	}

	public void setQuoteRef(String quoteRef) {
		this.quoteRef = quoteRef;
	}

	public int getExpiry_duration_days() {
		return expiry_duration_days;
	}

	public void setExpiry_duration_days(int expiry_duration_days) {
		this.expiry_duration_days = expiry_duration_days;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public LocalDate getDate_of_quote() {
		return date_of_quote;
	}

	public void setDate_of_quote(LocalDate date_of_quote) {
		this.date_of_quote = date_of_quote;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public KioskQuery getQ_kiosk() {
		return q_kiosk;
	}

	public void setQ_kiosk(KioskQuery q_kiosk) {
		this.q_kiosk = q_kiosk;
	}

	public List<Company> getCompanyList() {
		return companyList;
	}

	public void setCompanyList(List<Company> companyList) {
		this.companyList = companyList;
	}

	public byte[] getQuotationPDF() {
		return quotationPDF;
	}

	public void setQuotationPDF(byte[] quotationPDF) {
		this.quotationPDF = quotationPDF;
	}

	@Override
	public String toString() {
		return "AddQuote [quote_id=" + quote_id + ", staff_email_id=" + staff_email_id + ", kiosk_id=" + kiosk_id
				+ ", employee_id=" + employee_id + ", quoteRef=" + quoteRef + ", expiry_duration_days="
				+ expiry_duration_days + ", companyName=" + companyName + ", date_of_quote=" + date_of_quote
				+ ", startDate=" + startDate + ", endDate=" + endDate + ", q_kiosk=" + q_kiosk + ", companyList="
				+ companyList + "]";
	}

}
