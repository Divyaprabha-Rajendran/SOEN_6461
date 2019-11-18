package com.concordia.soen.sdm.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.concordia.soen.sdm.pojo.Client;
import com.concordia.soen.sdm.pojo.UserLogin;
import com.concordia.soen.sdm.tableDataGateway.ClientTableDataGateway;
import com.concordia.soen.sdm.tableDataGateway.LoginTableDataGateway;

/**
 * 
 * Mapper class for Login
 *
 */
public class LoginMapper {
	@Autowired
	LoginTableDataGateway loginTableDataGateway;

	@Autowired
	private HttpSession httpSession;

	public UserLogin userLogin(String name, String password) throws ClassNotFoundException, SQLException {
		UserLogin login = new UserLogin();
		UserLogin user = new UserLogin();
		login.setUserName(name);
		login.setPassword(password);
		ResultSet rs=loginTableDataGateway.select(login);
		while(rs.next()){
			user.setUserId(rs.getInt("userId"));
			user.setRole(rs.getString("role"));
			user.setUserName(rs.getString("userName"));
			user.setPassword(rs.getString("Password"));
		
		}
		return user;
	}
}
