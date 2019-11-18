package com.concordia.soen.sdm.tableDataGateway;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.concordia.soen.sdm.pojo.Client;
/**
 * 
 * Table data gateway class for clientmanagement
 *
 */
public class ClientTableDataGateway {

	
	/**
	 * THis method will insert a record in database
	 * @param Client object 
	 * @return Resultset object 
	 *
	 */
	public void insert(Client client) throws DuplicateKeyException,Exception {
		
		
		JdbcConnectionManager jdbc=JdbcConnectionManager.getjdbc();
		Connection connection=jdbc.getConnection(); 
		Statement statement = connection.createStatement();
		
		statement.addBatch("SET FOREIGN_KEY_CHECKS = 0");
		statement.addBatch("INSERT INTO VehicleRentingSystem.clientInformation(firstName,lastName,licenseNumber,licenseExpiryDate, phoneNo,version) VALUES ('"
				  + client.getFirstName()+"','" + client.getLastName()+"','"
				 +client.getLicenseNumber()+"','" +client.getLicenseExpiryDate()+"','"
				  +client.getPhoneNo()+"','" + client.getVersion()+"')");
		statement.addBatch("SET FOREIGN_KEY_CHECKS = 1");
		statement.executeBatch();	  
	}

	/**
	 * THis method will retrieve a record from database
	 * @param String licenseNumber 
	 * @return Resultset object 
	 *
	 */
	
	public ResultSet select(String licenseNumber) throws ClassNotFoundException, SQLException {
		JdbcConnectionManager jdbc=JdbcConnectionManager.getjdbc();
		Connection connection=jdbc.getConnection(); 
		Statement statement = connection.createStatement();
		String sql = "SELECT * FROM VehicleRentingSystem.clientInformation where licenseNumber='" +licenseNumber+"'";
		ResultSet rs = statement.executeQuery(sql);
		return rs; 
	}
	
	/**
	 * THis method will retrieve a record from database
	 * 
	 * @return Resultset object 
	 *
	 */
	
	public ResultSet selectMultipleRows() throws ClassNotFoundException, SQLException {
		JdbcConnectionManager jdbc=JdbcConnectionManager.getjdbc();
		Connection connection=jdbc.getConnection(); 
		Statement statement = connection.createStatement();
		String sql = "SELECT * FROM VehicleRentingSystem.clientInformation";
		ResultSet rs = statement.executeQuery(sql);
		return rs; 
	}

	/**
	 * THis method will update a record in database
	 * @param Client client
	 * @param int version
	 * @return Resultset object 
	 *
	 */
	
	public boolean update(Client client, int version) throws ClassNotFoundException, SQLException {
		
		JdbcConnectionManager jdbc=JdbcConnectionManager.getjdbc();
		Connection connection=jdbc.getConnection(); 
		Statement statement = connection.createStatement();
		String updateQuery = "UPDATE VehicleRentingSystem.clientInformation SET firstName='"+client.getFirstName()
		+"', lastName='"+client.getLastName()+"', licenseExpiryDate='"+client.getLicenseExpiryDate()+"', phoneNo='"
		+client.getPhoneNo()+"',version='"+client.getVersion()+"' WHERE licenseNumber='"+client.getLicenseNumber()+"' and version='"+version+"'";
		
		statement.addBatch("SET FOREIGN_KEY_CHECKS = 0");   
		statement.addBatch(updateQuery);
		statement.addBatch("SET FOREIGN_KEY_CHECKS = 1");
		int[] result=statement.executeBatch(); 
		 
			
		 if(result[1]<=0) {
			 return false;
		 }else {
			 return true;
			 
		 }
		
		
	}
	
	public void delete(String licenseNumber) throws ClassNotFoundException, SQLException {
		JdbcConnectionManager jdbc=JdbcConnectionManager.getjdbc();
		Connection connection=jdbc.getConnection(); 
		Statement statement = connection.createStatement();
		String deleteTransactionQuery= "DELETE FROM VehicleRentingSystem.rentedVehiclesRecord  WHERE licenseNumber='"+licenseNumber+"'";
		String deleteQuery = "DELETE FROM VehicleRentingSystem.clientInformation  WHERE licenseNumber='"+licenseNumber+"'";
		statement.addBatch("SET FOREIGN_KEY_CHECKS = 0");   
		statement.addBatch(deleteQuery);
		statement.addBatch(deleteTransactionQuery);
		statement.addBatch("SET FOREIGN_KEY_CHECKS = 1");
		int[] result=statement.executeBatch();
		
		
	}
	
	public ResultSet getVehicleRentRecordsForUsers(String licenseNumber) throws ClassNotFoundException, SQLException {
		JdbcConnectionManager jdbc=JdbcConnectionManager.getjdbc();
		Connection connection=jdbc.getConnection(); 
		Statement statement = connection.createStatement();
	
		String  selectQuery="select * from VehicleRentingSystem.rentedVehiclesRecord where licenseNumber='"+licenseNumber+"' and ( status='rented' or status='reserved')";
	ResultSet rs=statement.executeQuery(selectQuery);
	return rs;
	}


	
	
}
