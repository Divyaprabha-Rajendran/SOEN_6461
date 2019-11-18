package com.concordia.soen.sdm.pojo;

import java.sql.Date;
/**
 * @author Swetha Chenna
 * Pojo for Cancel Return.
 *
 */
public class CancelReturn {
	String type;
	String licensePlate;
	String availability;
	Date startDate;
	Date dueDate;
	String licenseNumber;
	int cost;
	String status;
	int reservationId;
	 /**
     * Getter method for getting status of vehicle
     *
     * @return String status
     */
	public String getStatus() {
		return status;
	}
	/**
     * Setter method for setting status
     *
     * @param String vehicle status
     */
	public void setStatus(String status) {
		this.status = status;
	}
	 /**
     * Getter method for getting reservation id
     *
     * @return int  reservationId
     */
	public int getReservationId() {
		return reservationId;
	}
	/**
     * Setter method for setting reservationId
     *
     * @param int reservationId
     */
	public void setReservationId(int reservationId) {
		this.reservationId = reservationId;
	}
	 /**
     * Getter method for getting vehicle type
     *
     * @return String  vehicle type
     */
	public String getType() {
		return type;
	}
	/**
     * Setter method for setting type
     *
     * @param String  vehicle type
     */
	public void setType(String type) {
		this.type = type;
	}
	 /**
     * Getter method for getting licensePlate
     *
     * @return String  licensePlate
     */
	public String getLicensePlate() {
		return licensePlate;
	}
	/**
     * Setter method for setting licensePlate
     *
     * @param String licensePlate
     */
	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}
	 /**
     * Getter method for getting availability
     *
     * @return String availability
     */
	public String getAvailability() {
		return availability;
	}
	/**
     * Setter method for setting availability
     *
     * @param String availability
     */
	public void setAvailability(String availability) {
		this.availability = availability;
	}
	 /**
     * Getter method for getting start date
     *
     * @return date  startDate
     */
	public Date getStartDate() {
		return startDate;
	}
	/**
     * Setter method for setting startDate
     *
     * @param date startDate
     */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	 /**
     * Getter method for getting dueDate
     *
     * @return date dueDate
     */
	public Date getDueDate() {
		return dueDate;
	}
	/**
     * Setter method for setting dueDate
     *
     * @param date dueDate
     */
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	 /**
     * Getter method for getting licenseNumber
     *
     * @return String licenseNumber
     */
	public String getLicenseNumber() {
		return licenseNumber;
	}
	/**
     * Setter method for setting licenseNumber
     *
     * @param String licenseNumber
     */
	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}
	 /**
     * Getter method for getting cost
     *
     * @return int  cost
     */
	public int getCost() {
		return cost;
	}
	/**
     * Setter method for setting cost
     *
     * @param int cost
     */
	public void setCost(int cost) {
		this.cost = cost;
	}
}
