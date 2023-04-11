package com.edenstar.model;

import javax.persistence.Embeddable;

@Embeddable
public class Rate {
	
	private int rateID;
	private int discountDurationDays;
	private double rateCoeff;
	private double rateMax;
	private double rateMin;
	private int zoneID;
	
	public Rate() {
		super();
	}

	public Rate(int rateID, int discountDurationDays, double rateCoeff, double rateMax, double rateMin, int zoneID) {
		super();
		this.rateID = rateID;
		this.discountDurationDays = discountDurationDays;
		this.rateCoeff = rateCoeff;
		this.rateMax = rateMax;
		this.rateMin = rateMin;
		this.zoneID = zoneID;
	}

	public int getRateID() {
		return rateID;
	}

	public void setRateID(int rateID) {
		this.rateID = rateID;
	}

	public int getDiscountDurationDays() {
		return discountDurationDays;
	}

	public void setDiscountDurationDays(int discountDurationDays) {
		this.discountDurationDays = discountDurationDays;
	}

	public double getRateCoeff() {
		return rateCoeff;
	}

	public void setRateCoeff(double rateCoeff) {
		this.rateCoeff = rateCoeff;
	}

	public double getRateMax() {
		return rateMax;
	}

	public void setRateMax(double rateMax) {
		this.rateMax = rateMax;
	}

	public double getRateMin() {
		return rateMin;
	}

	public void setRateMin(double rateMin) {
		this.rateMin = rateMin;
	}

	public int getZoneID() {
		return zoneID;
	}

	public void setZoneID(int zoneID) {
		this.zoneID = zoneID;
	}

	
	

} // Rate