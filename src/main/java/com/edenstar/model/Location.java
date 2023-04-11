package com.edenstar.model;

import javax.persistence.Embeddable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Embeddable
public class Location {

	private int locationID;
	private String locationName;
	@JsonInclude(Include.NON_DEFAULT)
	private String locationArea;
	private String mapURL;

	@JsonInclude(Include.NON_EMPTY)
	private byte[] locationMap;

	public Location() {
		super();
	}

	public Location(int locationID, String locationName, String locationArea, String mapURL, byte[] locationMap) {
		super();
		this.locationID = locationID;
		this.locationName = locationName;
		this.locationArea = locationArea;
		this.mapURL = mapURL;
		this.locationMap = locationMap;
	}

	public int getLocationID() {
		return locationID;
	}

	public void setLocationID(int locationID) {
		this.locationID = locationID;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public String getLocationArea() {
		return locationArea;
	}

	public void setLocationArea(String locationArea) {
		this.locationArea = locationArea;
	}

	public String getMapURL() {
		return mapURL;
	}

	public void setMapURL(String mapURL) {
		this.mapURL = mapURL;
	}

	public byte[] getLocationMap() {
		return locationMap;
	}

	public void setLocationMap(byte[] locationMap) {
		this.locationMap = locationMap;
	}

} // Location