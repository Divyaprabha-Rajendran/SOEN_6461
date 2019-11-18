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

	@Autowired
	VehicleReservationTableDataGateway vehicleReservationTableDataGateway;
	
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

	public void updateAvailability(String availability, String licensePlate) throws ClassNotFoundException, SQLException {
		vehicleReservationTableDataGateway.updateAvailability(availability,licensePlate);
		
	}


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

