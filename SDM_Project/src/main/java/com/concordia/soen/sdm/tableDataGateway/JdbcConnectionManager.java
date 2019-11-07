package com.concordia.soen.sdm.tableDataGateway;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;

public class JdbcConnectionManager {
	
	
	private static JdbcConnectionManager jdbc=new JdbcConnectionManager();
	 private JdbcConnectionManager(){}  
	  
	 public static JdbcConnectionManager getjdbc(){  
	  return jdbc;  
	 }  
	 
	  public Connection getConnection()throws ClassNotFoundException, SQLException  
      {  
		  String url = "jdbc:mysql://database-1.cbaso99rimof.us-east-1.rds.amazonaws.com:3306/VehicleRentingSystem"; 
	        String user = "HarishJayasankar"; 
	        String pass = "Harish2810"; 
            System.out.println("Connection 1");
          Connection con= DriverManager.getConnection(url,user,pass);
          System.out.println("Connection 2");
          return con;  
            
      }  

}
