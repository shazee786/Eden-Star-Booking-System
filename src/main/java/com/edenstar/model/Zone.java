package com.edenstar.model;

import javax.persistence.Embeddable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Embeddable
public class Zone {
	
	@JsonInclude(Include.NON_DEFAULT)
	private int zoneID;
	@JsonInclude(Include.NON_DEFAULT)
	private int zoneNumber;
	@JsonInclude(Include.NON_NULL)
	private String zoneName;
	@JsonInclude(Include.NON_DEFAULT)
	private int locationID;
	
	public Zone() {
		super();
	}

	public Zone(int zoneID, int zoneNumber, String zoneName, int locationID) {
		super();
		this.zoneID = zoneID;
		this.zoneNumber = zoneNumber;
		this.zoneName = zoneName;
		this.locationID = locationID;
	}

	public int getZoneID() {
		return zoneID;
	}

	public void setZoneID(int zoneID) {
		this.zoneID = zoneID;
	}

	public int getZoneNumber() {
		return zoneNumber;
	}

	public void setZoneNumber(int zoneNumber) {
		this.zoneNumber = zoneNumber;
	}

	public String getZoneName() {
		return zoneName;
	}

	public void setZoneName(String zoneName) {
		this.zoneName = zoneName;
	}

	public int getLocationID() {
		return locationID;
	}

	public void setLocationID(int locationID) {
		this.locationID = locationID;
	}
	

} // Zone