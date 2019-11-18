package com.concordia.soen.sdm.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.concordia.soen.sdm.pojo.CatalogDetails;
import com.concordia.soen.sdm.pojo.Client;
import com.concordia.soen.sdm.pojo.Transaction;
import com.concordia.soen.sdm.tableDataGateway.VehicleReservationTableDataGateway;

public class VehicleReservationMapper {

	/**
	 * 
	 *Mapper to VehicleReservationController.
	 *
	 */

	@Autowired
	VehicleReservationTableDataGateway vehicleReservationTableDataGateway;
	
	/**
	 * 
	 *Method to select a vehicle given a parameter.
	 *@param parameter parameter selected.
	 *@return CatalogDetails details of the vehicle to be returned.
	 */

	
	synchronized public CatalogDetails getVehicle(String parameter) throws ClassNotFoundException, SQLException {
		ResultSet rs=vehicleReservationTableDataGateway.selectVehicle(parameter);
		CatalogDetails vehicleDetails = new CatalogDetails();
		System.out.println("Select  Vehicles:Start");
		while(rs.next()){
			vehicleDetails.setVehicleId(rs.getInt("vehicleId"));
			vehicleDetails.setType(rs.getString("type"));
			vehicleDetails.setMake(rs.getString("make"));
			vehicleDetails.setModel(rs.getString("model"));
			vehicleDetails.setYear(rs.getInt("year"));
			vehicleDetails.setColor(rs.getString("color"));
			vehicleDetails.setLicensePlate(rs.getString("licensePlate"));
			vehicleDetails.setAvailability(rs.getString("availability"));
			vehicleDetails.setCost(rs.getInt("cost"));
			vehicleDetails.setVersion(rs.getInt("version"));
		}
		System.out.println("Select  Vehicles:Stop");
		return vehicleDetails;
	}

	/**
	 * Method to return all the clientdetails
	 *@return List<Client> list of all clientDetails.
	 *
	 */

	
	public List<Client> getAllClientDetails() throws ClassNotFoundException, SQLException {
		ResultSet rs=vehicleReservationTableDataGateway.getAllClientDetails();
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
	 *mathod to return a particular client given the parameter 
	 *@param String parameter parameter to select a client
	 *@return Client with 
	 */

	
	public Client select(String parameter) throws ClassNotFoundException, SQLException {
		ResultSet rs=vehicleReservationTableDataGateway.select(parameter);
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
	 * 
	 *Method to update the availability for the given licenseplate
	 *@param String availability availability to be updated.
	 *@param String licensePlate vehicle to be updated.
	 */

	
	public void updateAvailability(String availability, String licensePlate) throws ClassNotFoundException, SQLException {
		vehicleReservationTableDataGateway.updateAvailability(availability,licensePlate);
		
	}

	/**
	 * Method to insert reservation data to the table
	 * @param String licensePlate licensePlate of the vehicle
	 * @param String licenseNumber licenseNumber of the client
	 * @param String duedate duedate the vehicle to be returned.
	 * @param String startDate starting date of the reservation
	 * @param String status status to be updated for the reservation. 
	 * @param int cost cost per day of the vehicle
	 * @param int clientId client who is making the reservation.
	 * @param int vehicleId vehicle which is reserved.
	 * @return boolean update success or failure message.
	 */


	synchronized public boolean insertData(String licenseNumber, String licensePlate, String duedate, String startdate, String status,
			int cost, int clientId, int vehicleId) throws ClassNotFoundException, SQLException {
		Transaction transaction=new Transaction();
		transaction.setClientId(clientId);
		transaction.setLicenseNumber(licenseNumber);
		transaction.setLicensePlate(licensePlate);
		transaction.setDuedate(duedate);
		transaction.setStartdate(startdate);
		transaction.setStatus(status);
		transaction.setCost(cost);
		transaction.setVehicleId(vehicleId);
		System.out.println("Client Id "+clientId);
		System.out.println("License Plate "+licensePlate);
		boolean reservationUpdate=vehicleReservationTableDataGateway.insertData(transaction);
		return reservationUpdate;
	
		
	}

	
	/**
	 * 
	 *Method to return all the reservation record related to licenseplate
	 *@param String numberPlate licensePlate to be selected.  
	 *@return List<Transaction> records related to the licensePlate.
	 */

	public List<Transaction> getVehicleRentRecords(String numberPlate) throws SQLException, ClassNotFoundException {
		ResultSet rs=vehicleReservationTableDataGateway.getVehicleRentRecords(numberPlate);
		
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

