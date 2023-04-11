package com.edenstar.model.dash;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Embedded;

import com.edenstar.model.Kiosk;
import com.edenstar.model.Rate;
import com.edenstar.model.Zone;

public class CreateZone extends Zone {

	private String staff_email_id;

	@Embedded
	private Rate rate = new Rate();

	@Embedded
	private List<Kiosk> kioskList = new ArrayList<Kiosk>();

	public CreateZone() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CreateZone(int zoneID, int zoneNumber, String zoneName, int locationID) {
		super(zoneID, zoneNumber, zoneName, locationID);
		// TODO Auto-generated constructor stub
	}

	public String getStaff_email_id() {
		return staff_email_id;
	}

	public void setStaff_email_id(String staff_email_id) {
		this.staff_email_id = staff_email_id;
	}

	public Rate getRate() {
		return rate;
	}

	public void setRate(Rate rate) {
		this.rate = rate;
	}

	public List<Kiosk> getKioskList() {
		return kioskList;
	}

	public void setKioskList(List<Kiosk> kioskList) {
		this.kioskList = kioskList;
	}

} // CreatZone
