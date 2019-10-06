package com.concordia.soen.sdm.dao;

import java.sql.PreparedStatement;

import javax.sql.DataSource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.concordia.soen.sdm.pojo.UserLogin;


/** this class USERLOGINDAO is for the login of the user 
 * 
 */
public class UserLoginDAO{


	  @Autowired
	  DataSource datasource;
	  @Autowired
	  JdbcTemplate jdbcTemplate;
	  
	  /**
	   * this method userLogin takes username and password as arguments and thnen starts the user page 
	   * @param userName is of type string 
	   * @param password is of type strikng 
	   * @return
	   */
	public UserLogin userLogin(String userName, String password) {
		System.out.println("DAO:Start");
		  String sql = "SELECT * FROM Users  WHERE userName=? and Password=? ";
		  System.out.println("DAO:Stop");
	        return jdbcTemplate.queryForObject(sql, new Object[]{userName,password},
	                BeanPropertyRowMapper.newInstance(UserLogin.class));
		

	}
	

}