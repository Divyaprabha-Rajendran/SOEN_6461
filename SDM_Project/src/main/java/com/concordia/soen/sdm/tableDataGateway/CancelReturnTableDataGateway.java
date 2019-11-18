package com.concordia.soen.sdm.tableDataGateway;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.concordia.soen.sdm.pojo.CancelReturn;
/**
 * 
 * Table data gateway class for cancel Return 
 *
 */
public class CancelReturnTableDataGateway {
	
	/**
	 * THis method will insert a record in database
	 * @return Resultset object 
	 *
	 */

	public ResultSet selectMultipleRows() throws ClassNotFoundException, SQLException {
		JdbcConnectionManager jdbc=JdbcConnectionManager.getjdbc();
		Connection connection=jdbc.getConnection(); 
		Statement statement = connection.createStatement();
		String sql = "select VehicleDetails.type, rentedVehiclesRecord.licensePlate, VehicleDetails.availability,rentedVehiclesRecord.cost,\r\n" + 
				"rentedVehiclesRecord.licenseNumber, rentedVehiclesRecord.startdate, rentedVehiclesRecord.duedate,rentedVehiclesRecord.status\r\n" + 
				",rentedVehiclesRecord.reservationId from ((VehicleRentingSystem.rentedVehiclesRecord \r\n" + 
				"inner join VehicleRentingSystem.clientInformation ON  rentedVehiclesRecord.clientId = clientInformation.clientId)\r\n" + 
				"inner join VehicleRentingSystem.VehicleDetails  ON rentedVehiclesRecord.vehicleId = VehicleDetails.vehicleId) where \r\n" + 
				"rentedVehiclesRecord.status=\"reserved\" or rentedVehiclesRecord.status=\"rented\"";
	    System.out.println("Select cancel return multiple rows Statement:Start"); 
		ResultSet rs = statement.executeQuery(sql);
	    System.out.println("Select cancel return multiple rows Statement:Finish"); 
		return rs; 
		
		
		
	}

	/**
	 * THis method will update a record in database
	 * @param CancelReturn transaction
	 *
	 */
	public void update(CancelReturn transaction) throws ClassNotFoundException, SQLException {

		JdbcConnectionManager jdbc=JdbcConnectionManager.getjdbc();
		Connection connection=jdbc.getConnection(); 
		Statement statement = connection.createStatement();
		String updateQuery = "UPDATE VehicleRentingSystem.rentedVehiclesRecord SET status='"+transaction.getStatus()
		+"'WHERE reservationId='"+transaction.getReservationId()+"'";
		
		statement.addBatch("SET FOREIGN_KEY_CHECKS = 0");   
		statement.addBatch(updateQuery);
		statement.addBatch("SET FOREIGN_KEY_CHECKS = 1");
		System.out.println("update Statement:Start");
		statement.executeBatch();
		 System.out.println("update Statement:Stop"); 
		
	
	}

	/**
	 * THis method will update a record in database
	 *	@param CancelReturn transaction
	 *
	 */
	public void updateReturn(CancelReturn transaction) throws ClassNotFoundException, SQLException {
		JdbcConnectionManager jdbc=JdbcConnectionManager.getjdbc();
		Connection connection=jdbc.getConnection(); 
		Statement statement = connection.createStatement();
		String updateVehicleRecordQuery = "UPDATE VehicleRentingSystem.VehicleDetails SET availability='"+transaction.getAvailability()+"' where licensePlate='"+transaction.getLicensePlate()+"'";
		
		String updatetransactionRecordQuery ="UPDATE VehicleRentingSystem.rentedVehiclesRecord SET status='"+transaction.getStatus()
		+"'WHERE reservationId='"+transaction.getReservationId()+"'";
		
		statement.addBatch("SET FOREIGN_KEY_CHECKS = 0");   
		statement.addBatch(updateVehicleRecordQuery);
		statement.addBatch(updatetransactionRecordQuery);
		statement.addBatch("SET FOREIGN_KEY_CHECKS = 1");
		System.out.println("update Statement:Start");
		statement.executeBatch();
		 System.out.println("update Statement:Stop"); 
		
		
	}

}
