package com.concordia.soen.sdm.pojo;

public class UserLogin {

	private int userId;
    private String role;
    private String userName;
    private String Password;
    
    /**
     * Getter method for getting id
     *
     * @return String id
     */
	public int getUserId() {
		return userId;
	}
	
	/**
	 * setter for user id
	 * @param user id
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}
	/**
     * Getter method for getting role
     *
     * @return String role
     */
	public String getRole() {
		return role;
	}
	/**
	 * setter for role
	 * @param user role
	 */
	public void setRole(String role) {
		this.role = role;
	}
	
	/**
     * Getter method for getting username
     *
     * @return String user name
     */
	public String getUserName() {
		return userName;
	}
	/**
	 * setter for user name
	 * @param user name
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	/**
     * Getter method for getting password
     *
     * @return String password
     */
	public String getPassword() {
		return Password;
	}
	/**
	 * setter for userpassword
	 * @param user password
	 */
	public void setPassword(String password) {
		Password = password;
	}
}
