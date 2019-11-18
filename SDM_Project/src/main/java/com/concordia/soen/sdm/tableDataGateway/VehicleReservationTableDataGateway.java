package com.concordia.soen.sdm.tableDataGateway;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.concordia.soen.sdm.pojo.Transaction;

public class VehicleReservationTableDataGateway {
	
	/**
	 * Method to select multiple records in the table given a parameter.
	 * @param String parameter licensePlate to be selected.
	 * @return ResultSet Containing all the required data.
	 *
	 */


	public ResultSet selectVehicle(String parameter) throws ClassNotFoundException, SQLException {
		JdbcConnectionManager jdbc=JdbcConnectionManager.getjdbc();
		Connection connection=jdbc.getConnection(); 
		Statement statement = connection.createStatement();
		String sql = "SELECT * FROM VehicleDetails where licensePlate='"+parameter+"'";
	  
		ResultSet rs = statement.executeQuery(sql);
	    System.out.println("Select vehicles:Finish"); 
		return rs; 
	}

	/**
	 * Method to select multiple records in the table.
	 * @return ResultSet Containing all the required data.
	 *
	 */


	public ResultSet getAllClientDetails() throws ClassNotFoundException, SQLException {
		JdbcConnectionManager jdbc=JdbcConnectionManager.getjdbc();
		Connection connection=jdbc.getConnection(); 
		Statement statement = connection.createStatement();
		String sql = "SELECT * FROM VehicleRentingSystem.clientInformation";
	    System.out.println("Select multiple rows Statement:Start"); 
		ResultSet rs = statement.executeQuery(sql);
	    System.out.println("Select multiple rows Statement:Finish"); 
		return rs; 
	}

	/**
	 * Method to select multiple records in the table given the parameter,
	 * @param String parameter licenseNumber to be selected.
	 * @return ResultSet Containing all the required data.
	 *
	 */


	public ResultSet select(String parameter) throws ClassNotFoundException, SQLException {
		JdbcConnectionManager jdbc=JdbcConnectionManager.getjdbc();
		Connection connection=jdbc.getConnection(); 
		Statement statement = connection.createStatement();
		String sql = "SELECT * FROM VehicleRentingSystem.clientInformation where licenseNumber='" +parameter+"'";
	    System.out.println("Select Statement:Start"); 
		ResultSet rs = statement.executeQuery(sql);
		int fetchSize=rs.getFetchSize();
		System.out.println("Size:"+fetchSize);
	    System.out.println("Select Statement:Finish"); 
		return rs;
	}

	/**
	 * Method to updateAvailability for a given vehicle.
	 * @param String availability availibility of the vehicle
	 * @param String licensePlate licenseplate to be updated.
	 *
	 */


	public void updateAvailability(String availability, String licensePlate) throws SQLException, ClassNotFoundException {
		JdbcConnectionManager jdbc=JdbcConnectionManager.getjdbc();
		Connection connection=jdbc.getConnection(); 
		Statement statement = connection.createStatement();
		String sql = "UPDATE VehicleDetails SET availability='"+availability+"' where licensePlate='"+licensePlate+"'";
		statement.addBatch("SET FOREIGN_KEY_CHECKS = 0");   
		statement.addBatch(sql);
		statement.addBatch("SET FOREIGN_KEY_CHECKS = 1");
		System.out.println("update Statement:Start");
		statement.executeBatch();
		 System.out.println("update Statement:Stop"); 
		  
	
		
	}


	/**
	 * Method to insert data into the table.
	 * @param transaction to be inserted.
	 *
	 */

	public boolean insertData(Transaction transaction) throws SQLException, ClassNotFoundException {
		JdbcConnectionManager jdbc=JdbcConnectionManager.getjdbc();
		Connection connection=jdbc.getConnection(); 
		Statement statement = connection.createStatement();
	
		String  selectQuery="select * from VehicleRentingSystem.rentedVehiclesRecord where vehicleId='"+transaction.getVehicleId()+"' and NOT status='cancelled' and NOT status='returned' AND (startdate='"+transaction.getStartdate()+"' OR startdate='"+transaction.getDuedate()+"')";
	ResultSet check=statement.executeQuery(selectQuery);
	if (!check.next()) {
		String insertQuery = "INSERT INTO VehicleRentingSystem.rentedVehiclesRecord(vehicleId,clientId,startdate,duedate,licenseNumber,licensePlate, status, cost) VALUES ('"
				+ transaction.getVehicleId()+"','"
						+ transaction.getClientId()+"','"
				+ transaction.getStartdate()+"','"
				+ transaction.getDuedate()+"','"
				+transaction.getLicenseNumber()+"','"
				+transaction.getLicensePlate()+"','"
				+transaction.getStatus()+"','"
				+ transaction.getCost()+"')";
		    System.out.println("Insert Statement:Start"); 
			int rs = statement.executeUpdate(insertQuery);
		    System.out.println("Insert Statement:Finish"); 
		    
		    return true;
	}else {
		return false;
	}
		  
		
	}

	/**
	 * Method to select multiple records in the table given the licensePlate.
	 * @param String numberPlate licensePlate of the vehicle.
	 * @return ResultSet Containing all the required data.
	 *
	 */


	public ResultSet getVehicleRentRecords(String numberPlate) throws ClassNotFoundException, SQLException {
		JdbcConnectionManager jdbc=JdbcConnectionManager.getjdbc();
		Connection connection=jdbc.getConnection(); 
		Statement statement = connection.createStatement();
		
		String  selectQuery="select * from VehicleRentingSystem.rentedVehiclesRecord where licensePlate='"+numberPlate+"' and (NOT status='cancelled' and NOT status='returned') ";
	ResultSet rs=statement.executeQuery(selectQuery);
	return rs;
	}
	
	

}

