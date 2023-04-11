package com.edenstar.model.booking;

public class QueryLocation {

	private String staff_email_id;
	private int location_id;
	private int kiosk_id;
	private String startDate;
	private String endDate;

	public QueryLocation() {
		super();
	}

	public QueryLocation(String staff_email_id, int location_id, int kiosk_id, String startDate, String endDate) {
		super();
		this.staff_email_id = staff_email_id;
		this.location_id = location_id;
		this.kiosk_id = kiosk_id;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public String getStaff_email_id() {
		return staff_email_id;
	}

	public void setStaff_email_id(String staff_email_id) {
		this.staff_email_id = staff_email_id;
	}

	public int getLocation_id() {
		return location_id;
	}

	public void setLocation_id(int location_id) {
		this.location_id = location_id;
	}

	public int getKiosk_id() {
		return kiosk_id;
	}

	public void setKiosk_id(int kiosk_id) {
		this.kiosk_id = kiosk_id;
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

}
