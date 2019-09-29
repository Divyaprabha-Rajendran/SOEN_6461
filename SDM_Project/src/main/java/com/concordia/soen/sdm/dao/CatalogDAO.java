package com.concordia.soen.sdm.dao;
import javax.sql.DataSource;
import com.concordia.soen.sdm.pojo.CatalogDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.util.List;
import java.sql.Connection;

public class CatalogDAO {
	  @Autowired
	  DataSource datasource;
	  @Autowired
	  JdbcTemplate jdbcTemplate;
	 
	public void newLogin() {
		
		//String insetQuery = "insert into Users (userId,role,userName,Password) values(?,?,?,?)";
		/*
		 * try { ptmt = getConnection().prepareStatement(insetQuery); ptmt.setInt(1, 2);
		 * ptmt.setString(2, "clerk"); ptmt.setString(3, "Harriish"); ptmt.setString(4,
		 * "123456789");
		 * 
		 * } catch (SQLException e) { System.out.println("Unable to create statement.");
		 * } int no = 0; System.out.println(insetQuery); try { ptmt.execute();
		 * System.out.println("executed"); } catch (SQLException e) {
		 * System.out.println("Unable to execute query."); } return no;
		 */
	
	}

	public List<CatalogDetails> all(){
		return jdbcTemplate.query("SELECT * FROM VehicleDetails",
              BeanPropertyRowMapper.newInstance(CatalogDetails.class));
		
	}

}
