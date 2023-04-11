package com.edenstar.model.booking;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Embedded;

import com.edenstar.model.Kiosk;
import com.edenstar.model.Zone;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class KioskQuery extends Kiosk {

	private String availability_status;
	private String availability_color;
	private String location_name;
	@JsonInclude(Include.NON_DEFAULT)
	private int location_grid_row_max;
	@JsonInclude(Include.NON_DEFAULT)
	private int location_grid_column_max;
	private Zone zone;

	@JsonInclude(Include.NON_DEFAULT)
	private double daily_rate;
	@JsonInclude(Include.NON_DEFAULT)
	private double lease_total;
	@JsonInclude(Include.NON_DEFAULT)
	private int number_days_available;
	@JsonInclude(Include.NON_DEFAULT)
	private int lease_duration;

	
	@JsonInclude(Include.NON_EMPTY)
	@Embedded
	private List<DateSlice> dateList = new ArrayList<DateSlice>();

	public KioskQuery() {
		super();
		// TODO Auto-generated constructor stub
	}

	public KioskQuery(int kioskID, int gridLocationRow, int gridLocationColumn, int isLocked, int isVoid,
			int kioskNumber, int zoneID) {
		super(kioskID, gridLocationRow, gridLocationColumn, isLocked, isVoid, kioskNumber, zoneID);
		// TODO Auto-generated constructor stub
	}

	public KioskQuery(String availability_status, String availability_color, String location_name,
			int location_grid_row_max, int location_grid_column_max, Zone zone, double daily_rate, double lease_total,
			int number_days_available, int lease_duration, List<DateSlice> dateList) {
		super();
		this.availability_status = availability_status;
		this.availability_color = availability_color;
		this.location_name = location_name;
		this.location_grid_row_max = location_grid_row_max;
		this.location_grid_column_max = location_grid_column_max;
		this.zone = zone;
		this.daily_rate = daily_rate;
		this.lease_total = lease_total;
		this.number_days_available = number_days_available;
		this.lease_duration = lease_duration;
		this.dateList = dateList;
	}

	public String getAvailability_status() {
		return availability_status;
	}

	public void setAvailability_status(String availability_status) {
		this.availability_status = availability_status;
	}

	public String getAvailability_color() {
		return availability_color;
	}

	public void setAvailability_color(String availability_color) {
		this.availability_color = availability_color;
	}

	public String getLocation_name() {
		return location_name;
	}

	public void setLocation_name(String location_name) {
		this.location_name = location_name;
	}

	public int getLocation_grid_row_max() {
		return location_grid_row_max;
	}

	public void setLocation_grid_row_max(int location_grid_row_max) {
		this.location_grid_row_max = location_grid_row_max;
	}

	public int getLocation_grid_column_max() {
		return location_grid_column_max;
	}

	public void setLocation_grid_column_max(int location_grid_column_max) {
		this.location_grid_column_max = location_grid_column_max;
	}

	public Zone getZone() {
		return zone;
	}

	public void setZone(Zone zone) {
		this.zone = zone;
	}

	public double getDaily_rate() {
		return daily_rate;
	}

	public void setDaily_rate(double daily_rate) {
		this.daily_rate = daily_rate;
	}

	public double getLease_total() {
		return lease_total;
	}

	public void setLease_total(double lease_total) {
		this.lease_total = lease_total;
	}

	public int getNumber_days_available() {
		return number_days_available;
	}

	public void setNumber_days_available(int number_days_available) {
		this.number_days_available = number_days_available;
	}

	public int getLease_duration() {
		return lease_duration;
	}

	public void setLease_duration(int lease_duration) {
		this.lease_duration = lease_duration;
	}

	public List<DateSlice> getDateList() {
		return dateList;
	}

	public void setDateList(List<DateSlice> dateList) {
		this.dateList = dateList;
	}

	@Override
	public String toString() {
		return "KioskQuery [availability_status=" + availability_status + ", availability_color=" + availability_color
				+ ", location_name=" + location_name + ", location_grid_row_max=" + location_grid_row_max
				+ ", location_grid_column_max=" + location_grid_column_max + ", zone=" + zone + ", daily_rate="
				+ daily_rate + ", lease_total=" + lease_total + ", number_days_available=" + number_days_available
				+ ", lease_duration=" + lease_duration + ", dateList=" + dateList + "]";
	}

}
