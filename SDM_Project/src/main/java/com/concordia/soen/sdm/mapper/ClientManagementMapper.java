package com.concordia.soen.sdm.mapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import com.concordia.soen.sdm.pojo.Client;
import com.concordia.soen.sdm.pojo.Transaction;
import com.concordia.soen.sdm.tableDataGateway.ClientTableDataGateway;
/**
 * 
 * Mapper class for clientmanagement
 *
 */
public class ClientManagementMapper {
	
	
	@Autowired
	ClientTableDataGateway clientTableDataGateway;

	@Autowired
	private HttpSession httpSession;
	
	/**
	 * THis method will insert a record in database
	 * @param firstName,lastName,licenseNumber,phoneNo,licenseexpiryDate 
	 *  
	 *
	 */
	
	public void insert(String firstName, String lastName, String licenseNumber, String phoneNo, Date dateDB) throws DuplicateKeyException,Exception{
		Client client = new Client();
		client.setFirstName(firstName);
		client.setLastName(lastName);
		client.setLicenseNumber(licenseNumber);
		client.setPhoneNo(phoneNo);
		client.setLicenseExpiryDate(dateDB);
		client.setVersion(1);
		clientTableDataGateway.insert(client);
		
		
		
	}
	/**
	 * THis method will retrieve a record from database
	 * @param String licenseNumber 
	 * @return Client client 
	 *
	 */
	public Client select(String licenseNumber) throws ClassNotFoundException, SQLException {
		ResultSet rs=clientTableDataGateway.select(licenseNumber);
		Client client = new Client();
		
		while(rs.next()){
			client.setClientId(rs.getInt("clientId"));
			client.setFirstName(rs.getString("firstName"));
			client.setLastName(rs.getString("lastName"));
			client.setLicenseNumber(rs.getString("licenseNumber"));
			client.setLicenseExpiryDate(rs.getDate("licenseExpiryDate"));
			client.setPhoneNo(rs.getString("phoneNo"));
	        client.setVersion(rs.getInt("version"));
		}
		
		return client;
	}
	
	/**
	 * THis method will retrieve a record from database
	 * 
	 * @return List<Client> clientList
	 *
	 */
	public List<Client> selectMultipleRows() throws ClassNotFoundException, SQLException {
		
		ResultSet rs=clientTableDataGateway.selectMultipleRows();
		List<Client> clientList =new ArrayList<Client>();
		
		while(rs.next()){
			Client client = new Client();
			client.setClientId(rs.getInt("clientId"));
			client.setFirstName(rs.getString("firstName"));
			client.setLastName(rs.getString("lastName"));
			client.setLicenseNumber(rs.getString("licenseNumber"));
			client.setLicenseExpiryDate(rs.getDate("licenseExpiryDate"));
			client.setPhoneNo(rs.getString("phoneNo"));
	        client.setVersion(rs.getInt("version"));
	        clientList.add(client);
		}
		
		return  clientList;
	}
	

	/**
	 * THis method will update a record in database
	 * @param firstName,lastName,licenseNumber,phoneNo,licenseexpiryDate 
	 * @return boolean updateflag
	 *
	 */
	public boolean update(String firstName, String lastName, String licenseNumber, String phoneNo, Date dateDB,
			int version) throws ClassNotFoundException, SQLException {
		
		Client client = new Client();
		int recordVersion=version+1;
		client.setFirstName(firstName);
		client.setLastName(lastName);
		client.setLicenseNumber(licenseNumber);
		client.setPhoneNo(phoneNo);
		client.setLicenseExpiryDate(dateDB);
		client.setVersion(recordVersion);
	boolean updateCheck=clientTableDataGateway.update(client,version);
	
	return updateCheck;
	}
	
	public void delete(String licenseNumber) throws ClassNotFoundException, SQLException {
		clientTableDataGateway.delete(licenseNumber);
		
	}
	
	public List<Transaction> getVehicleRentRecordsForUsers(String licenseNumber) throws SQLException, ClassNotFoundException {
		ResultSet rs=clientTableDataGateway.getVehicleRentRecordsForUsers(licenseNumber);
		
		List<Transaction> transactionlist=new ArrayList<Transaction>();
		while(rs.next()) {
			Transaction transaction=new Transaction();
			transaction.setClientId(rs.getInt("clientId"));
			transaction.setVehicleId(rs.getInt("vehicleId"));
			transaction.setReservationId(rs.getInt("reservationId"));
			transaction.setStartdate(rs.getString("startdate"));
			transaction.setDuedate(rs.getString("duedate"));
			transaction.setLicenseNumber(rs.getString("licenseNumber"));
			transaction.setLicensePlate(rs.getString("licensePlate"));
			transaction.setStatus(rs.getString("status"));
			transaction.setCost(rs.getInt("cost"));	
			transactionlist.add(transaction);
		}
		return transactionlist;
	}
	


	
	

}
