package com.concordia.soen.sdm.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.concordia.soen.sdm.pojo.Client;
import com.concordia.soen.sdm.pojo.Transaction;
import com.concordia.soen.sdm.tableDataGateway.ClientTableDataGateway;
import com.concordia.soen.sdm.tableDataGateway.TransactionHistoryTableDataGateway;
/**
 * 
 * Mapper class for TransactionHistory
 *
 */
public class TransactionHistoryMapper {
	@Autowired
	TransactionHistoryTableDataGateway transactionHistoryTableDataGateway;

	/**
	 * THis method will select all record in database
	 * @return list of transaction
	 *  
	 *
	 */
	public List<Transaction> selectAll()throws ClassNotFoundException, SQLException {
		ResultSet rs=transactionHistoryTableDataGateway.selectMultipleRows();
		List<Transaction> transactionList =new ArrayList<Transaction>();
		
		while(rs.next()){
			Transaction transaction = new Transaction();
			
			transaction.setReservationId(rs.getInt("reservationId"));
			transaction.setStartdate(rs.getString("startdate"));
			transaction.setDuedate(rs.getString("duedate"));
			transaction.setLicenseNumber(rs.getString("licenseNumber"));
			transaction.setLicensePlate(rs.getString("licensePlate"));
			transaction.setStatus(rs.getString("status"));
			transaction.setCost(rs.getInt("cost"));
			transactionList.add(transaction);
			
		}
		
		return  transactionList;
	}
	
	/**
	 * THis method will retrieve a record from database according to duedate
	 * @param String datestr 
	 * @return <List>Transaction transactionList 
	 *
	 */
	public List<Transaction> selectTransactionRecordDate(String dateStr) throws SQLException, ClassNotFoundException {
		ResultSet rs=transactionHistoryTableDataGateway.selectRecordDate(dateStr);
		List<Transaction> transactionList =new ArrayList<Transaction>();
		
		while(rs.next()){
			Transaction transaction = new Transaction();
			
			transaction.setReservationId(rs.getInt("reservationId"));
			transaction.setStartdate(rs.getString("startdate"));
			transaction.setDuedate(rs.getString("duedate"));
			transaction.setLicenseNumber(rs.getString("licenseNumber"));
			transaction.setLicensePlate(rs.getString("licensePlate"));
			transaction.setStatus(rs.getString("status"));
			transaction.setCost(rs.getInt("cost"));
			transactionList.add(transaction);
		}
		
		return transactionList;
	}

	/**
	 * THis method will retrieve a record from database according to licensenumber
	 * @param String  searchdata licensenumber
	 * @return <List>Transaction transactionList 
	 *
	 */
	public List<Transaction> selectTransactionRecordClientId(String searchData) throws SQLException, ClassNotFoundException {
		ResultSet rs=transactionHistoryTableDataGateway.selectRecordClientId(searchData);
		List<Transaction> transactionList =new ArrayList<Transaction>();
		
		while(rs.next()){
			Transaction transaction = new Transaction();
			
			transaction.setReservationId(rs.getInt("reservationId"));
			transaction.setStartdate(rs.getString("startdate"));
			transaction.setDuedate(rs.getString("duedate"));
			transaction.setLicenseNumber(rs.getString("licenseNumber"));
			transaction.setLicensePlate(rs.getString("licensePlate"));
			transaction.setStatus(rs.getString("status"));
			transaction.setCost(rs.getInt("cost"));
			transactionList.add(transaction);
		}
		
		return transactionList;
	}

	/**
	 * THis method will retrieve a record from database according to vehicle license plate
	 * @param String  searchdata license plate number
	 * @return <List>Transaction transactionList 
	 *
	 */
	
	public List<Transaction> selectTransactionRecordVehicleId(String searchData) throws SQLException, ClassNotFoundException {
		ResultSet rs=transactionHistoryTableDataGateway.selectRecordVehicleId(searchData);
		List<Transaction> transactionList =new ArrayList<Transaction>();
		
		while(rs.next()){
			Transaction transaction = new Transaction();
			
			transaction.setReservationId(rs.getInt("reservationId"));
			transaction.setStartdate(rs.getString("startdate"));
			transaction.setDuedate(rs.getString("duedate"));
			transaction.setLicenseNumber(rs.getString("licenseNumber"));
			transaction.setLicensePlate(rs.getString("licensePlate"));
			transaction.setStatus(rs.getString("status"));
			transaction.setCost(rs.getInt("cost"));
			transactionList.add(transaction);
		}
		
		return transactionList;
	}

	/**
	 * THis method will retrieve a record from database regarding vehicles which are out
	 * @param String  status rented
	 * @return <List>Transaction transactionList 
	 *
	 */
	public List<Transaction> selectTransactionRentedVehicles(String status) throws ClassNotFoundException, SQLException {
		ResultSet rs=transactionHistoryTableDataGateway.selectTransactionRentedVehicles(status);
		List<Transaction> transactionList =new ArrayList<Transaction>();
		
		while(rs.next()){
			Transaction transaction = new Transaction();
			
			transaction.setReservationId(rs.getInt("reservationId"));
			transaction.setStartdate(rs.getString("startdate"));
			transaction.setDuedate(rs.getString("duedate"));
			transaction.setLicenseNumber(rs.getString("licenseNumber"));
			transaction.setLicensePlate(rs.getString("licensePlate"));
			transaction.setStatus(rs.getString("status"));
			transaction.setCost(rs.getInt("cost"));
			transactionList.add(transaction);
		}
		
		return transactionList;
	}
	}


