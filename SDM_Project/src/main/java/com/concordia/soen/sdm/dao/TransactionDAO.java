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
	  public Transaction getTransactionID(int reservationId) {
		  System.out.println("DAO:Start");
		  String sql = "SELECT * FROM rentedVehiclesRecord  WHERE reservationId=?";
		  System.out.println("DAO:Stop");
		  
		return jdbcTemplate.queryForObject(sql, new Object[]{reservationId},
	                BeanPropertyRowMapper.newInstance(Transaction.class));
		  
	  }
	  public Transaction getTransactionStatus(String status) {
		  System.out.println("DAO:Start");
		  String sql = "SELECT status FROM rentedVehiclesRecord where reservationId=?;";
		  System.out.println("DAO:Stop");
		return jdbcTemplate.queryForObject(sql, new Object[]{status},
                BeanPropertyRowMapper.newInstance(Transaction.class));
		  
	  }
	  
	  public Transaction getreturnsave(Transaction status) {
		  System.out.println("DAO:Start");
		  String sql = "update rentedVehiclesRecord set status=\"available\";";
		  System.out.println("DAO:Stop");
		return jdbcTemplate.queryForObject(sql, new Object[]{status},
	                BeanPropertyRowMapper.newInstance(Transaction.class));
		  
	  }
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
	public List<Transaction> getAllTransactions() {
		String sql = "SELECT * FROM VehicleRentingSystem.rentedVehiclesRecord";
		return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Transaction.class));
	}
}
