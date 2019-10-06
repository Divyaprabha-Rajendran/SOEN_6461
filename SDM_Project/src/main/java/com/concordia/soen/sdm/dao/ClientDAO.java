package com.concordia.soen.sdm.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.concordia.soen.sdm.pojo.Client;

/**
 * @author Harish Jayasankar
 *DAO for Client management system.
 *
 */
public class ClientDAO {

	@Autowired
	DataSource datasource;
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	/**
	 * Method to particular client detail
	 * @param String Client ID 
	 * @return  query will run and give success message.
	 */
	public Client getClientDetails(String clientId) throws Exception {
		System.out.println("DAO:Start");
		String sql = "SELECT * FROM clientInformation  WHERE licenseNumber=?";
		System.out.println("DAO:Stop");
		return jdbcTemplate.queryForObject(sql, new Object[]{clientId},
				BeanPropertyRowMapper.newInstance(Client.class));


	}

	/**
	 * Method to get all client details
	 * @param String Client ID 
	 * @return  query will run and give success message.
	 */
	public List<Client> getAllClientDetails()throws Exception {
		
		
		String sql = "SELECT * FROM clientInformation";
		return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Client.class));
		
		}

	/**
	 * Method to create client detail
	 * @param Client client model classes 
	 * @return  query will run and give success message.
	 */
	public void insertClientDetails(Client client) throws Exception{
		String insertQuery = "INSERT INTO VehicleRentingSystem.clientInformation(firstName,lastName,licenseNumber,licenseExpiryDate, phoneNo) VALUES ('"
				+ client.getFirstName()+"','"
				+ client.getLastName()+"','"
				+client.getLicenseNumber()+"','"
				+client.getLicenseExpiryDate()+"','"
				+ client.getPhoneNo()+"')";
		jdbcTemplate.execute(insertQuery);			
	}

	/**
	 * Method to update client detail
	 * @param Client client model classes 
	 * @return  query will run and give success message.
	 */
	public void updateClientDetails(Client client) {
		String updateQuery = "UPDATE VehicleRentingSystem.clientInformation SET firstName='"+client.getFirstName()
		+"', lastName='"+client.getLastName()+"', licenseExpiryDate='"+client.getLicenseExpiryDate()+"', phoneNo='"
		+client.getPhoneNo()+"' WHERE licenseNumber='"+client.getLicenseNumber()+"'";
		jdbcTemplate.execute(updateQuery);
	}

	/**
	 * Method to delete client detail
	 * @param license number 
	 * @return  query will run and give success message.
	 */
	public void deleteClient(String licenseNumber) throws Exception{
		String sql = "DELETE FROM VehicleRentingSystem.clientInformation  WHERE licenseNumber=?";
		jdbcTemplate.update(sql, new Object[]{licenseNumber});
	}
}
