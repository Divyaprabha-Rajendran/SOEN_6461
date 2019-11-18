package com.concordia.soen.sdm.tableDataGateway;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.concordia.soen.sdm.pojo.Transaction;
/**
 * 
 * Table data gateway class for transaction history
 *
 */
public class TransactionHistoryTableDataGateway {
	
	/**
	 * THis method will insert a record in database
	 * @return Resultset object 
	 *
	 */
	public ResultSet selectMultipleRows() throws ClassNotFoundException, SQLException {
		JdbcConnectionManager jdbc=JdbcConnectionManager.getjdbc();
		Connection connection=jdbc.getConnection(); 
		Statement statement = connection.createStatement();
		String sql = "SELECT * FROM VehicleRentingSystem.rentedVehiclesRecord";
	    System.out.println("Select multiple rows Statement:Start"); 
		ResultSet rs = statement.executeQuery(sql);
	    System.out.println("Select multiple rows Statement:Finish"); 
		return rs; 
	}


	/**
	 * THis method will retrieve a record from database with respect to vehicle number
	 * @param String vehicle number 
	 * @return Resultset object 
	 *
	 */

	public ResultSet selectRecordVehicleId(String searchData) throws ClassNotFoundException, SQLException {
		JdbcConnectionManager jdbc=JdbcConnectionManager.getjdbc();
		Connection connection=jdbc.getConnection(); 
		Statement statement = connection.createStatement();
		String sql = "SELECT * FROM VehicleRentingSystem.rentedVehiclesRecord WHERE licensePlate='"+searchData+"' and (NOT status='cancelled' and NOT status='returned') ";
		ResultSet rs = statement.executeQuery(sql);
		return rs; 
	}
	
	/**
	 * THis method will retrieve a record from database with respect to license number
	 * @param String license number 
	 * @return Resultset object 
	 *
	 */

	public ResultSet selectRecordClientId(String searchData) throws ClassNotFoundException, SQLException {
		JdbcConnectionManager jdbc=JdbcConnectionManager.getjdbc();
		Connection connection=jdbc.getConnection(); 
		Statement statement = connection.createStatement();
		String sql = "SELECT * FROM VehicleRentingSystem.rentedVehiclesRecord WHERE licenseNumber='"+searchData+"'";
		ResultSet rs = statement.executeQuery(sql);
		return rs; 
	}

	/**
	 * THis method will retrieve a record from database with respect to due date
	 * @param String due date 
	 * @return Resultset object 
	 *
	 */
	public ResultSet selectRecordDate(String dateStr)throws ClassNotFoundException, SQLException {
		JdbcConnectionManager jdbc=JdbcConnectionManager.getjdbc();
		Connection connection=jdbc.getConnection(); 
		Statement statement = connection.createStatement();
		String sql = "SELECT * FROM VehicleRentingSystem.rentedVehiclesRecord WHERE duedate='"+dateStr+"'";
		ResultSet rs = statement.executeQuery(sql);
		return rs; 
	}

	/**
	 * THis method will retrieve a record from database with respect to status
	 * @param String status rented
	 * @return Resultset object 
	 *
	 */
	public ResultSet selectTransactionRentedVehicles(String status) throws ClassNotFoundException, SQLException {
		JdbcConnectionManager jdbc=JdbcConnectionManager.getjdbc();
		Connection connection=jdbc.getConnection(); 
		Statement statement = connection.createStatement();
		String sql = "SELECT * FROM VehicleRentingSystem.rentedVehiclesRecord WHERE status='"+status+"'";
		ResultSet rs = statement.executeQuery(sql);
		return rs; 
	}


	
	
	
}
