package com.concordia.soen.sdm.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;


import com.concordia.soen.sdm.pojo.CatalogDetails;


public class CatalogDAO {

	@Autowired
	DataSource datasource;
	@Autowired
	JdbcTemplate jdbcTemplate;

	public List<CatalogDetails> all() {
		return jdbcTemplate.query("SELECT * FROM VehicleDetails",
				BeanPropertyRowMapper.newInstance(CatalogDetails.class));
	}
	
	public CatalogDetails getVehicleDetails(String licensePlate) {
		String sql = "SELECT * FROM VehicleDetails  WHERE licensePlate=?";
		return jdbcTemplate.queryForObject(sql, new Object[]{licensePlate},
				BeanPropertyRowMapper.newInstance(CatalogDetails.class));
	}

	public void updateAvailability(String availability, String licenseNumber) {
		jdbcTemplate.execute("UPDATE VehicleDetails SET availability='"+availability+"' where licensePlate='"+licenseNumber+"'");
	}
}
