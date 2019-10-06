package com.concordia.soen.sdm.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.concordia.soen.sdm.pojo.Client;
import com.concordia.soen.sdm.pojo.Transaction;

public class TransactionDAO {
	 @Autowired
	  DataSource datasource;
	  @Autowired
	  JdbcTemplate jdbcTemplate;
	  
	  /**
	   * getTransactiopnID is a method that takes reservation id as an argument and searches with reservationid in the database
	   * @param reservationId is of type int 
	   * @return
	   */
	  public Transaction getTransactionID(int reservationId) {
		  System.out.println("DAO:Start");
		  String sql = "SELECT * FROM rentedVehiclesRecord  WHERE reservationId=?";
		  System.out.println("DAO:Stop");
		  
		return jdbcTemplate.queryForObject(sql, new Object[]{reservationId},
	                BeanPropertyRowMapper.newInstance(Transaction.class));
		  
	  }
	  
	  /**
	   * it gets the transaction status by taking the status of the 
	   * @param status is of type string 
	   * @return
	   */
	  public Transaction getTransactionStatus(String status) {
		  System.out.println("DAO:Start");
		  String sql = "SELECT status FROM rentedVehiclesRecord where reservationId=?;";
		  System.out.println("DAO:Stop");
		return jdbcTemplate.queryForObject(sql, new Object[]{status},
                BeanPropertyRowMapper.newInstance(Transaction.class));
		  
	  }
	  /**
	   * get return save method takes status as argument and update the rented vehicle record and then sets it available
	   * @param status
	   * @return
	   */
	  public Transaction getreturnsave(Transaction status) {
		  System.out.println("DAO:Start");
		  String sql = "update rentedVehiclesRecord set status=\"available\";";
		  System.out.println("DAO:Stop");
		return jdbcTemplate.queryForObject(sql, new Object[]{status},
	                BeanPropertyRowMapper.newInstance(Transaction.class));
		  
	  }
	  
	  /**
	   * this method insert data is to enter all the details for renting the vehicle 
	   * @param transaction
	   */
	  
	public void insertData(Transaction transaction) {
		String insertQuery = "INSERT INTO VehicleRentingSystem.rentedVehiclesRecord(startdate,duedate,licenseNumber,licensePlate, status, cost) VALUES ('"
				+ transaction.getStartdate()+"','"
				+ transaction.getDuedate()+"','"
				+transaction.getLicenseNumber()+"','"
				+transaction.getLicensePlate()+"','"
				+transaction.getStatus()+"','"
				+ transaction.getCost()+"')";
		jdbcTemplate.execute(insertQuery);
	}
	
	
	/**
	 * getAllTransactions method gets all details of all  the transactions of the vehicles from the system 
	 * @return
	 */
	public List<Transaction> getAllTransactions() {
		String sql = "SELECT * FROM VehicleRentingSystem.rentedVehiclesRecord";
		return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Transaction.class));
	}
}
