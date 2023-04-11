package com.edenstar.model.dash;

import com.edenstar.model.Kiosk;

public class CreateKiosk extends Kiosk {
	
	private String staff_email_id;
	private int location_id;
	private int max_rows;
	private int max_columns;
	private String voidKiosk ="";
	private String lockKiosk ="";

	public int getMax_rows() {
		return max_rows;
	}

	public void setMax_rows(int max_rows) {
		this.max_rows = max_rows;
	}

	public String getVoidKiosk() {
		return voidKiosk;
	}

	public void setVoidKiosk(String voidKiosk) {
		this.voidKiosk = voidKiosk;
	}

	public String getLockKiosk() {
		return lockKiosk;
	}

	public void setLockKiosk(String lockKiosk) {
		this.lockKiosk = lockKiosk;
	}

	public int getMax_columns() {
		return max_columns;
	}

	public void setMax_columns(int max_columns) {
		this.max_columns = max_columns;
	}

	public int getLocation_id() {
		return location_id;
	}

	public void setLocation_id(int location_id) {
		this.location_id = location_id;
	}

	public CreateKiosk() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CreateKiosk(int kioskID, int gridLocationRow, int gridLocationColumn, int isLocked, int isVoid,
			int kioskNumber, int zoneID) {
		super(kioskID, gridLocationRow, gridLocationColumn, isLocked, isVoid, kioskNumber, zoneID);
		// TODO Auto-generated constructor stub
	}

	public String getStaff_email_id() {
		return staff_email_id;
	}

	public void setStaff_email_id(String staff_email_id) {
		this.staff_email_id = staff_email_id;
	}
	
	

} // CreateKiosk
