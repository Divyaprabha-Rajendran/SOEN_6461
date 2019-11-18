package com.concordia.soen.sdm.tableDataGateway;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.concordia.soen.sdm.pojo.UserLogin;
/**
 * 
 * Table Data Gateway class for login management.
 *
 */
public class LoginTableDataGateway {

	

	public ResultSet select(UserLogin login) throws ClassNotFoundException, SQLException {
		JdbcConnectionManager jdbc=JdbcConnectionManager.getjdbc();
		Connection connection=jdbc.getConnection(); 
		Statement statement = connection.createStatement();
		String sql = "SELECT * FROM Users  WHERE userName='" +login.getUserName()+"' and Password='"+login.getPassword()+"'";
		ResultSet rs = statement.executeQuery(sql);
		return rs;
		
	}

}
