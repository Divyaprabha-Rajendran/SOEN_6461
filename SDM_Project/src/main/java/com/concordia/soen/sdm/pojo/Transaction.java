package com.concordia.soen.sdm.pojo;

import java.util.Date;


/**
 * @author Divyaprabha Rajendran
 * Model class for Transaction representing reservation
 *
 */
public class Transaction {

	private int reservationId;
	private Date startdate;
	private Date duedate;
	private String licenseNumber;
	private String licensePlate;
	private String status;
	private int cost;
	/**
	 * getter for reservationId
	 * @return reservationId
	 */
	
	public int getReservationId() {
		return reservationId;
	}
	
	/**
	 * setter for reservationId
	 * @param reservationId
	 */
	public void setReservationId(int reservationId) {
		this.reservationId = reservationId;
	}
	/**
	 * getter for startdate
	 * @return startdate
	 */
	public Date getStartdate() {
		return startdate;
	}
	/**
	 * setter for startdate
	 * @param startdate
	 */
	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}
	/**
	 * getter for duedate
	 * @return duedate
	 */
	public Date getDuedate() {
		return duedate;
	}
	/**
	 * setter for duedate
	 * @param duedate
	 */
	public void setDuedate(Date duedate) {
		this.duedate = duedate;
	}
	/**
	 * getter for licenseNumber
	 * @return licenseNumber
	 */
	public String getLicenseNumber() {
		return licenseNumber;
	}
	/**
	 * setter for licenseNumber
	 * @param licenseNumber
	 */
	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}
	/**
	 * getter for licensePlate
	 * @return licensePlate
	 */
	public String getLicensePlate() {
		return licensePlate;
	}
	/**
	 * setter for licensePlate
	 * @param licensePlate
	 */
	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}
	/**
	 * getter for status
	 * @return status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * setter for status
	 * @param status
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * getter for cost
	 * @return cost
	 */
	public int getCost() {
		return cost;
	}
	/**
	 * setter for cost
	 * @param cost
	 */
	public void setCost(int cost) {
		this.cost = cost;
	}
}
