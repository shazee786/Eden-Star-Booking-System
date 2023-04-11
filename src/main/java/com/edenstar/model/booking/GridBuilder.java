package com.edenstar.model.booking;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class GridBuilder {
	private String locationName;
	private int zoneNumber;
	private String availability_color;
	private String availability_status;
	private int location_grid_row_max;
	private int location_grid_column_max;

	@JsonInclude(Include.NON_DEFAULT)
	private double lease_total;

	private int kioskID;
	private int gridLocationRow;
	private int gridLocationColumn;
	private int kioskNumber;

	public GridBuilder() {
		super();
	}

	public GridBuilder(String locationName, int zoneNumber, String availability_color, String availability_status,
			int location_grid_row_max, int location_grid_column_max, double lease_total, int kioskID,
			int gridLocationRow, int gridLocationColumn, int kioskNumber) {
		super();
		this.locationName = locationName;
		this.zoneNumber = zoneNumber;
		this.availability_color = availability_color;
		this.availability_status = availability_status;
		this.location_grid_row_max = location_grid_row_max;
		this.location_grid_column_max = location_grid_column_max;
		this.lease_total = lease_total;
		this.kioskID = kioskID;
		this.gridLocationRow = gridLocationRow;
		this.gridLocationColumn = gridLocationColumn;
		this.kioskNumber = kioskNumber;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public int getZoneNumber() {
		return zoneNumber;
	}

	public void setZoneNumber(int zoneNumber) {
		this.zoneNumber = zoneNumber;
	}

	public String getAvailability_color() {
		return availability_color;
	}

	public void setAvailability_color(String availability_color) {
		this.availability_color = availability_color;
	}

	public String getAvailability_status() {
		return availability_status;
	}

	public void setAvailability_status(String availability_status) {
		this.availability_status = availability_status;
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

	public double getLease_total() {
		return lease_total;
	}

	public void setLease_total(double lease_total) {
		this.lease_total = lease_total;
	}

	public int getKioskID() {
		return kioskID;
	}

	public void setKioskID(int kioskID) {
		this.kioskID = kioskID;
	}

	public int getGridLocationRow() {
		return gridLocationRow;
	}

	public void setGridLocationRow(int gridLocationRow) {
		this.gridLocationRow = gridLocationRow;
	}

	public int getGridLocationColumn() {
		return gridLocationColumn;
	}

	public void setGridLocationColumn(int gridLocationColumn) {
		this.gridLocationColumn = gridLocationColumn;
	}

	public int getKioskNumber() {
		return kioskNumber;
	}

	public void setKioskNumber(int kioskNumber) {
		this.kioskNumber = kioskNumber;
	}

	

}
