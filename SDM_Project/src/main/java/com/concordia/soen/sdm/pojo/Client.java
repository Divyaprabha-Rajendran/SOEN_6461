package com.concordia.soen.sdm.pojo;

import java.io.Serializable;
import java.sql.Date;
/**
 * @author Harish Jayasankar
 * Pojo for Client management system.
 *
 */
public class Client implements Serializable{
	
	private String firstName ;
	private String lastName;
	private String  DOB;
	private String licenseNumber;
	private Date licenseExpiryDate;
	private String phoneNo;
	private int version;
	private int clientId;
	
	
	/**
     * Getter method for getting clientId
     *
     * @return int cientId
     */
    public int getClientId() {
		return clientId;
	}
    /**
     * Setter method for setting clientId
     *
     * @param int clientId
     */
	public void setClientId(int clientId) {
		this.clientId = clientId;
	}
	/**
     * Getter method for getting version
     *
     * @return int version
     */
	public int getVersion() {
		return version;
	}
	  /**
     * Setter method for setting record version
     *
     * @param int version
     */
	public void setVersion(int version) {
		this.version = version;
	}
	
    /**
     * Getter method for getting first name
     *
     * @return String first name
     */
	public String getFirstName() {
		return firstName;
	}
	/**
     * Setter method for setting first name
     *
     * @param String first name
     */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
     * Getter method for getting last name
     *
     * @return String last name
     */
	public String getLastName() {
		return lastName;
	}
	/**
     * Setter method for setting last name
     *
     * @param String last name
     */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	/**
     * Getter method for getting dob
     *
     * @return String dob
     */
	public String getDOB() {
		return DOB;
	}
	/**
     * Setter method for setting dob
     *
     * @param String dob
     */
	public void setDOB(String dOB) {
		DOB = dOB;
	}
	/**
     * Getter method for getting license number
     *
     * @return String license number
     */
	public String getLicenseNumber() {
		return licenseNumber;
	}
	/**
     * Setter method for setting license number
     *
     * @param String license number
     */
	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}
	/**
     * Getter method for getting license expiry date
     *
     * @return Date license expiry date
     */
	public Date getLicenseExpiryDate() {
		return licenseExpiryDate;
	}
	
	/**
     * Setter method for setting license expiry date
     *
     * @param Date licenseexpirydate
     */
	public void setLicenseExpiryDate(Date licenseExpiryDate) {
		this.licenseExpiryDate = licenseExpiryDate;
	}
	/**
     * Getter method for getting phone number
     *
     * @return String phone number
     */
	public String getPhoneNo() {
		return phoneNo;
	}
	
	/**
     * Setter method for setting phone number
     *
     * @param String phone number
     */
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	
	
	

}
