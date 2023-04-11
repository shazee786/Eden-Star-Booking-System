package com.edenstar.model;

import java.util.Date;

public class Booking {

	private int bookingId;
	private int applicationId;
	private int calenderId;
	private int cutomerId;
	private int managerID;
	private int employeeID;

	private String comments;
	private Date dateOfBooking;
	private int leaseDurationDays;
	private Date leaseEndDate;
	private Date leaseStartDate;
	private double rate;

	public Booking() {
	}

	public Booking(int bookingId, int applicationId, int calenderId, int cutomerId, int managerID, int employeeID,
			String comments, Date dateOfBooking, int leaseDurationDays, Date leaseEndDate, Date leaseStartDate,
			double rate) {
		super();
		this.bookingId = bookingId;
		this.applicationId = applicationId;
		this.calenderId = calenderId;
		this.cutomerId = cutomerId;
		this.managerID = managerID;
		this.employeeID = employeeID;
		this.comments = comments;
		this.dateOfBooking = dateOfBooking;
		this.leaseDurationDays = leaseDurationDays;
		this.leaseEndDate = leaseEndDate;
		this.leaseStartDate = leaseStartDate;
		this.rate = rate;
	}

	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	public int getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(int applicationId) {
		this.applicationId = applicationId;
	}

	public int getCalenderId() {
		return calenderId;
	}

	public void setCalenderId(int calenderId) {
		this.calenderId = calenderId;
	}

	public int getCutomerId() {
		return cutomerId;
	}

	public void setCutomerId(int cutomerId) {
		this.cutomerId = cutomerId;
	}

	public int getManagerID() {
		return managerID;
	}

	public void setManagerID(int managerID) {
		this.managerID = managerID;
	}

	public int getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Date getDateOfBooking() {
		return dateOfBooking;
	}

	public void setDateOfBooking(Date dateOfBooking) {
		this.dateOfBooking = dateOfBooking;
	}

	public int getLeaseDurationDays() {
		return leaseDurationDays;
	}

	public void setLeaseDurationDays(int leaseDurationDays) {
		this.leaseDurationDays = leaseDurationDays;
	}

	public Date getLeaseEndDate() {
		return leaseEndDate;
	}

	public void setLeaseEndDate(Date leaseEndDate) {
		this.leaseEndDate = leaseEndDate;
	}

	public Date getLeaseStartDate() {
		return leaseStartDate;
	}

	public void setLeaseStartDate(Date leaseStartDate) {
		this.leaseStartDate = leaseStartDate;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

}