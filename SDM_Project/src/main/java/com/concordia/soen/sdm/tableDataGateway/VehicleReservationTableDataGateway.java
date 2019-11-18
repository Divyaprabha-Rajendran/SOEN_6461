package com.concordia.soen.sdm.tableDataGateway;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.concordia.soen.sdm.pojo.Transaction;

public class VehicleReservationTableDataGateway {

	public ResultSet selectVehicle(String parameter) throws ClassNotFoundException, SQLException {
		JdbcConnectionManager jdbc=JdbcConnectionManager.getjdbc();
		Connection connection=jdbc.getConnection(); 
		Statement statement = connection.createStatement();
		String sql = "SELECT * FROM VehicleDetails where licensePlate='"+parameter+"'";
	  
		ResultSet rs = statement.executeQuery(sql);
	    System.out.println("Select vehicles:Finish"); 
		return rs; 
	}

	

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
		connection.close();  
	
		
	}



	public boolean insertData(Transaction transaction) throws SQLException, ClassNotFoundException {
		JdbcConnectionManager jdbc=JdbcConnectionManager.getjdbc();
		Connection connection=jdbc.getConnection(); 
		Statement statement = connection.createStatement();
	
		String  selectQuery="select * from VehicleRentingSystem.rentedVehiclesRecord where vehicleId='"+transaction.getVehicleId()+"'AND startdate='"+transaction.getStartdate()+"'";
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
		    connection.close();
		    return true;
	}else {
		return false;
	}
		  
		
	}



	public ResultSet getVehicleRentRecords(String numberPlate) throws ClassNotFoundException, SQLException {
		JdbcConnectionManager jdbc=JdbcConnectionManager.getjdbc();
		Connection connection=jdbc.getConnection(); 
		Statement statement = connection.createStatement();
	
		String  selectQuery="select * from VehicleRentingSystem.rentedVehiclesRecord where vehicleId='"+numberPlate+"' and NOT status='cancelled'";
	ResultSet rs=statement.executeQuery(selectQuery);
	return rs;
	}
	
	

}

