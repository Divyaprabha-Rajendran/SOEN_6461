package com.concordia.soen.sdm.pojo;
/**
 * @author Harish Jayasankar
 * Pojo for Client management system.
 *
 */
public class CatalogDetails {
	private int  vehicleId;	
	private String  type;
	private String  make;
	private String  model;
	private int  year;
	private String  color;
	private String  licensePlate;
	private String  availability;
	private int cost;
	 /**
     * Getter method for getting vehicle id
     *
     * @return int  vehicle id
     */
	public int getVehicleId() {
		return vehicleId;
	}
	/**
     * Setter method for setting vehicle id
     *
     * @param int  vehicle id
     */
	public void setVehicleId(int vehicleId) {
		this.vehicleId = vehicleId;
	}
	 /**
     * Getter method for getting type
     *
     * @return String type
     */
	public String getType() {
		return type;
	}
	/**
     * Setter method for setting vehicle id
     *
     * @param String type
     */
	public void setType(String type) {
		this.type = type;
	}
	 /**
     * Getter method for getting make
     *
     * @return String make
     */
	public String getMake() {
		return make;
	}
	/**
     * Setter method for setting make
     *
     * @param String make
     */
	public void setMake(String make) {
		this.make = make;
	}
	 /**
     * Getter method for getting model
     *
     * @return String model
     */
	public String getModel() {
		return model;
	}
	/**
     * Setter method for setting model
    *
    * @param String model
    */
	public void setModel(String model) {
		this.model = model;
	} 
	/**
     * Getter method for getting year
    *
    * @return int year
    */
	public int getYear() {
		return year;
	}
	/**
     * Setter method for setting year
    *
    * @param int year
    */
	public void setYear(int year) {
		this.year = year;
	}
	/**
     * Getter method for getting color
    *
    * @return string color
    */
	public String getColor() {
		return color;
	}
	/**
     * setter method for setting color
    *
    * @param string color
    */
	public void setColor(String color) {
		this.color = color;
	}
	/**
     * Getter method for getting licenseplate
    *
    * @return string licenseplate
    */
	public String getLicensePlate() {
		return licensePlate;
	}
	/**
     * setter method for setting licensePlate
    *
    * @param string licensePlate
    */
	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}
	/**
     * Getter method for getting availability
    *
    * @return string availability
    */
	public String getAvailability() {
		return availability;
	}
	/**
     * setter method for setting availability
    *
    * @param string availability
    */
	public void setAvailability(String availability) {
		this.availability = availability;
	}
	/**
     * Getter method for getting cost
    *
    * @return int cost
    */
	public int getCost() {
		return cost;
	}
	/**
     * setter method for setting cost
    *
    * @param int cost
    */
	public void setCost(int cost) {
		this.cost = cost;
	}
	

}
