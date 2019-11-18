package com.concordia.soen.sdm.tableDataGateway;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * 
 * Table data gateway class for catalogcontroller
 *
 */
public class ClerkCatalogTableDataGateway {
	/**
	 * THis method will read a record from database
	 * @return Resultset object 
	 *
	 */
	public ResultSet selectMultipleRows() throws ClassNotFoundException, SQLException {
		JdbcConnectionManager jdbc=JdbcConnectionManager.getjdbc();
		Connection connection=jdbc.getConnection(); 
		Statement statement = connection.createStatement();
		String sql = "SELECT * FROM VehicleDetails";
	    System.out.println("Select multiple rows Statement:Start"); 
		ResultSet rs = statement.executeQuery(sql);
	    System.out.println("Select multiple rows Statement:Finish"); 
		return rs; 
	}

}
