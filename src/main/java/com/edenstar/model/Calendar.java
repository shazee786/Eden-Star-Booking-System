package com.edenstar.model;

import java.util.Date;

public class Calendar {

    private int calendarID;
    private int kioskID;
    private int leaseDurationDays;
    private Date leaseEndDate;
    private Date leaseStartDate;

    public Calendar() {
    }

    public Calendar(int calenderID, int kioskID, int leaseDurationDays, Date leaseEndDate, Date leaseStartDate) {
        this.calendarID = calenderID;
        this.kioskID = kioskID;
        this.leaseDurationDays = leaseDurationDays;
        this.leaseEndDate = leaseEndDate;
        this.leaseStartDate = leaseStartDate;
    }

    public int getCalendarID() {
        return calendarID;
    }

    public void setCalendarID(int calenderID) {
        this.calendarID = calenderID;
    }

    public int getKioskID() {
        return kioskID;
    }

    public void setKioskID(int kioskID) {
        this.kioskID = kioskID;
    }

    public int getLeaseDurationDays() {
        return leaseDurationDays;
    }

    public void setLeaseDurationDays(int leaseDurationDays) {
        this.leaseDurationDays = leaseDurationDays;
    }

    public Date getLeaseEndDate() {
        return leaseEndDate;
    }

    public void setLeaseEndDate(Date leaseEndDate) {
        this.leaseEndDate = leaseEndDate;
    }

    public Date getLeaseStartDate() {
        return leaseStartDate;
    }

    public void setLeaseStartDate(Date leaseStartDate) {
        this.leaseStartDate = leaseStartDate;
    }

    @Override
    public String toString() {
        return "Calendar{" +
                "calendarID=" + calendarID +
                ", kioskID=" + kioskID +
                ", leaseDurationDays=" + leaseDurationDays +
                ", leaseEndDate=" + leaseEndDate +
                ", leaseStartDate=" + leaseStartDate +
                '}';
    }
}
