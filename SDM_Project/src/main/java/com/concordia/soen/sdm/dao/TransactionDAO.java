package com.concordia.soen.sdm.dao;

import java.util.Date;
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
	 * @return list of transactions
	 */
	public List<Transaction> getAllTransactions() {
		String sql = "SELECT * FROM VehicleRentingSystem.rentedVehiclesRecord";
		return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Transaction.class));
	}
	/**
	 * getVehicleRentRecords method gets record of all vehicles that are rented
	 * @return list of transactions
	 */
	public List<Transaction> getVehicleRentRecords(String numberPlate) {
		String sql = "SELECT * FROM rentedVehiclesRecord where licensePlate='"+numberPlate+"';";
		return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Transaction.class));
	}
	
	/**
	 *This methods fetch the transaction records based on client license number
	 * @return list of transactions
	 */
	public List<Transaction> getUserTransactions(String licenseNumber) throws Exception {
		String sql = "SELECT * FROM VehicleRentingSystem.rentedVehiclesRecord WHERE licenseNumber=?";
		return jdbcTemplate.query(sql, new Object[]{licenseNumber},
                BeanPropertyRowMapper.newInstance(Transaction.class));
		
	}
	
	/*
	 * public List<Transaction> getVehicleDateTransactions(String licensePlate, Date
	 * dateStr) { String sql =
	 * "SELECT * FROM VehicleRentingSystem.rentedVehiclesRecord WHERE licensePlate=? and "
	 * ; return jdbcTemplate.query(sql, new Object[]{licenseNumber},
	 * BeanPropertyRowMapper.newInstance(Transaction.class));
	 * 
	 * }
	 */
	/**
	 *This methods fetch the transaction records based on vehicle license plate
	 * @return list of transactions
	 */
	public List<Transaction> getVehicleTransactions(String searchData) throws Exception {
		String sql = "SELECT * FROM VehicleRentingSystem.rentedVehiclesRecord WHERE licensePlate=?";
		return jdbcTemplate.query(sql, new Object[]{searchData},
                BeanPropertyRowMapper.newInstance(Transaction.class));
	}

	/**
	 *This methods fetch the transaction records based on due date
	 * @return list of transactions
	 */
	public List<Transaction> getdueDateTransactions(Date dueDate) throws Exception {
		String sql = "SELECT * FROM VehicleRentingSystem.rentedVehiclesRecord WHERE duedate=?";
		return jdbcTemplate.query(sql, new Object[]{dueDate},
                BeanPropertyRowMapper.newInstance(Transaction.class));
	}

	/**
	 *This methods fetch the transaction records based on transaction status="rented"
	 * @return list of transactions
	 */
	public List<Transaction> getrentedVehicleTransactions(String status) throws Exception {
		String sql = "SELECT * FROM VehicleRentingSystem.rentedVehiclesRecord WHERE status=?";
		return jdbcTemplate.query(sql, new Object[]{status},
                BeanPropertyRowMapper.newInstance(Transaction.class));
	}
	
	
}
