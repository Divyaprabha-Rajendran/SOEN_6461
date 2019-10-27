package com.concordia.soen.sdm.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.concordia.soen.sdm.pojo.Transaction;
import com.concordia.soen.sdm.pojo.VehicleDetails;

public class VehicleDao {
	@Autowired
	DataSource datasource;
	@Autowired
	JdbcTemplate jdbcTemplate;

	public List<VehicleDetails> getAllVehicles() {
		String sql = "SELECT * FROM VehicleDetails";
		return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(VehicleDetails.class));
	}

	public void deleteVehicle(String licensePlate) {
		String sql = "DELETE FROM VehicleDetails  WHERE licensePlate=?";
		jdbcTemplate.update(sql, new Object[]{licensePlate});
	}

	public List<VehicleDetails> getVechicleDetail(String licensePlate) {
		String sql = "SELECT * FROM VehicleDetails where licensePlate='"+licensePlate+"';";
		System.out.println(sql);
		return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(VehicleDetails.class));
	}

	public void updateRecord(VehicleDetails vehicle) {
		// TODO Auto-generated method stub
		String updateQuery = "UPDATE VehicleDetails SET type='"+ vehicle.getType()+"',"+
				   "make='"+ vehicle.getMake()+"',"+
				"model='"+vehicle.getModel()+"',"+
				"year='"+vehicle.getYear()+"',"+
				"color='"+vehicle.getColor()+"',"+
				"availability='"+vehicle.getAvailability()+"',"+
				"cost='"+ vehicle.getCost()+"'"+
				" WHERE licensePlate='"+vehicle.getLicensePlate()+"'";
		System.out.println(updateQuery);
		jdbcTemplate.execute(updateQuery);
		
	}
}
