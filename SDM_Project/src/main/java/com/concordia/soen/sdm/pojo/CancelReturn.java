package com.concordia.soen.sdm.pojo;

import java.sql.Date;

public class CancelReturn {
	String type;
	String licensePlate;
	String availability;
	Date startDate;
	Date dueDate;
	String licenseNumber;
	int cost;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getLicensePlate() {
		return licensePlate;
	}
	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}
	public String getAvailability() {
		return availability;
	}
	public void setAvailability(String availability) {
		this.availability = availability;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	public String getLicenseNumber() {
		return licenseNumber;
	}
	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
}
