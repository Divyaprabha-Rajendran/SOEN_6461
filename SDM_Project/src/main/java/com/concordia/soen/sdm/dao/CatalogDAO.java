package com.concordia.soen.sdm.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.concordia.soen.sdm.pojo.CancelReturn;
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
	public List<CancelReturn> getRentedVehicles() {
		String sql = "select v.type, v.licensePlate, v.availability,x.cost,x.licenseNumber, x.startdate, x.duedate from\r\n" + 
				"(select DISTINCT(a.licensePlate), max(a.startdate) as startdate,a.licenseNumber, b.cost, a.duedate from rentedVehiclesRecord a right join rentedVehiclesRecord b on a.licensePlate = b.licensePlate and a.startdate=b.startdate group by a.licensePlate)\r\n" + 
				" x left join VehicleDetails v on x.licensePlate= v.licensePlate where v.availability='NO'";
		return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(CancelReturn.class));
	}
}
