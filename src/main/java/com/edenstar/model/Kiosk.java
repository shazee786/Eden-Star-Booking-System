package com.edenstar.model;

import javax.persistence.Embeddable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Embeddable
public class Kiosk {

	private int kioskID;
	@JsonInclude(Include.NON_DEFAULT)
	private int gridLocationRow;
	@JsonInclude(Include.NON_DEFAULT)
	private int gridLocationColumn;
	@JsonInclude(Include.NON_DEFAULT)
	private int isLocked;
	@JsonInclude(Include.NON_DEFAULT)
	private int isVoid;
	@JsonInclude(Include.NON_DEFAULT)
	private int kioskNumber;
	@JsonInclude(Include.NON_DEFAULT)
	private int zoneID;

	public Kiosk() {
		super();
	}

	public Kiosk(int kioskID, int gridLocationRow, int gridLocationColumn, int isLocked, int isVoid, int kioskNumber,
			int zoneID) {
		super();
		this.kioskID = kioskID;
		this.gridLocationRow = gridLocationRow;
		this.gridLocationColumn = gridLocationColumn;
		this.isLocked = isLocked;
		this.isVoid = isVoid;
		this.kioskNumber = kioskNumber;
		this.zoneID = zoneID;
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

	public int getIsLocked() {
		return isLocked;
	}

	public void setIsLocked(int isLocked) {
		this.isLocked = isLocked;
	}

	public int getIsVoid() {
		return isVoid;
	}

	public void setIsVoid(int isVoid) {
		this.isVoid = isVoid;
	}

	public int getKioskNumber() {
		return kioskNumber;
	}

	public void setKioskNumber(int kioskNumber) {
		this.kioskNumber = kioskNumber;
	}

	public int getZoneID() {
		return zoneID;
	}

	public void setZoneID(int zoneID) {
		this.zoneID = zoneID;
	}

} // Kiosk