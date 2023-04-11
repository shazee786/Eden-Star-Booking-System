package com.edenstar.model.booking;

//this represents one day of the date specified by the user
//i.e. there would be 5 daySlice objects for duration between
//01/06/2019 and 05/06/2019

import java.util.Date;

import javax.persistence.Embeddable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Embeddable
public class DateSlice {

	private String kioskCalanderDATE;
	// @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy",
	// timezone="BST")
	@JsonInclude(Include.NON_NULL)
	private Date date;
	private boolean isAvailable;
	@JsonInclude(Include.NON_DEFAULT)
	private int bookingID;
	@JsonInclude(Include.NON_DEFAULT)
	private int calenderID;

	public DateSlice() {
		super();
	}

	public DateSlice(String kioskCalanderDATE, Date date, boolean isAvailable, int bookingID, int calenderID) {
		super();
		this.kioskCalanderDATE = kioskCalanderDATE;
		this.date = date;
		this.isAvailable = isAvailable;
		this.bookingID = bookingID;
		this.calenderID = calenderID;
	}

	public String getKioskCalanderDATE() {
		return kioskCalanderDATE;
	}

	public void setKioskCalanderDATE(String kioskCalanderDATE) {
		this.kioskCalanderDATE = kioskCalanderDATE;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public int getBookingID() {
		return bookingID;
	}

	public void setBookingID(int bookingID) {
		this.bookingID = bookingID;
	}

	public int getCalenderID() {
		return calenderID;
	}

	public void setCalenderID(int calenderID) {
		this.calenderID = calenderID;
	}

	@Override
	public String toString() {
		return "DateSlice [kioskCalanderDATE=" + kioskCalanderDATE + ", date=" + date + ", isAvailable=" + isAvailable
				+ ", bookingID=" + bookingID + ", calenderID=" + calenderID + "]";
	}

}
