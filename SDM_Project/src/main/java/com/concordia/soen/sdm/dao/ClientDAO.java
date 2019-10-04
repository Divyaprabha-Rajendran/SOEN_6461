package com.concordia.soen.sdm.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.concordia.soen.sdm.pojo.Client;

public class ClientDAO {

	@Autowired
	DataSource datasource;
	@Autowired
	JdbcTemplate jdbcTemplate;
	public Client getClientDetails(String clientId) throws Exception {
		System.out.println("DAO:Start");
		String sql = "SELECT * FROM clientInformation  WHERE licenseNumber=?";
		System.out.println("DAO:Stop");
		return jdbcTemplate.queryForObject(sql, new Object[]{clientId},
				BeanPropertyRowMapper.newInstance(Client.class));


	}

	public List<Client> getAllClientDetails()throws Exception {
		
		
		String sql = "SELECT * FROM clientInformation";
		return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Client.class));
		
		}

	public void insertClientDetails(Client client) throws Exception{
		String insertQuery = "INSERT INTO VehicleRentingSystem.clientInformation(firstName,lastName,licenseNumber,licenseExpiryDate, phoneNo) VALUES ('"
				+ client.getFirstName()+"','"
				+ client.getLastName()+"','"
				+client.getLicenseNumber()+"','"
				+client.getLicenseExpiryDate()+"','"
				+ client.getPhoneNo()+"')";
		jdbcTemplate.execute(insertQuery);			
	}

	public void updateClientDetails(Client client) {
		String updateQuery = "UPDATE VehicleRentingSystem.clientInformation SET firstName='"+client.getFirstName()
		+"', lastName='"+client.getLastName()+"', licenseExpiryDate='"+client.getLicenseExpiryDate()+"', phoneNo='"
		+client.getPhoneNo()+"' WHERE licenseNumber='"+client.getLicenseNumber()+"'";
		jdbcTemplate.execute(updateQuery);
	}

	public void deleteClient(String licenseNumber) {
		String sql = "DELETE FROM VehicleRentingSystem.clientInformation  WHERE licenseNumber=?";
		System.out.println("DAO:Stop");
		jdbcTemplate.update(sql, new Object[]{licenseNumber});
	}
}
