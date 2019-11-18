package com.concordia.soen.sdm.mapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.concordia.soen.sdm.pojo.CatalogDetails;
import com.concordia.soen.sdm.pojo.Client;
import com.concordia.soen.sdm.pojo.Transaction;
import com.concordia.soen.sdm.pojo.VehicleDetails;
import com.concordia.soen.sdm.tableDataGateway.ClientTableDataGateway;
import com.concordia.soen.sdm.tableDataGateway.VehicleRecordTableDataGateway;

/**
 * 
 * Mapper class for VehicleRecordManagement
 *
 */

/**
 * 
 *
 *
 */


public class VehicleRecordMapper {

	
	@Autowired
	VehicleRecordTableDataGateway vehicleRecordTableDataGateway;

	@Autowired
	private HttpSession httpSession;
	
	/**
	 * Method to insert data into the VehicleDatabase
	 * @param String type type of the car
	 * @param String model model of the car
	 * @param String make make model of the car
	 * @param int year year model of the car
	 * @param String color color model of the car
	 * @param String licensePlate licensePlate of the car
	 * @param int availability availability of the car
	 * @param int cost cost of the car per day
	 */

	
	public void insert(String type, String make, String model, int year, String color, String licensePlate, String availability, int cost) throws Exception{
		CatalogDetails newVehicle = new CatalogDetails();
	
		System.out.println("insert   Vehicles:Start");
		newVehicle.setType(type);
		newVehicle.setMake(make);
		newVehicle.setModel(model);
		newVehicle.setYear(year);
		newVehicle.setColor(color);
		newVehicle.setLicensePlate(licensePlate);
		newVehicle.setAvailability(availability);
		newVehicle.setCost(cost);
		newVehicle.setVersion(1);
		
		vehicleRecordTableDataGateway.insert(newVehicle);
		
		System.out.println("update   Vehicles:Stop");
		
	}
	
	/**
	 * Method to return all the vehicles in the table. 
	 * @return List<CatalogDetails> list of all vehicles.
	 *
	 */


	public List<CatalogDetails> selectAllVehicles() throws SQLException, ClassNotFoundException {
		ResultSet rs=vehicleRecordTableDataGateway.selectMultipleRows();
		List<CatalogDetails> vehicleList =new ArrayList<CatalogDetails>();
		
		System.out.println("Select All Vehicles:Start");
		while(rs.next()){
			CatalogDetails vehicleDetails = new CatalogDetails();
	
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
			vehicleList.add(vehicleDetails);
			
		}
		System.out.println("Select All Vehicles:Stop");
		
		return  vehicleList;
	}
	
	/**
	 * Select a particular vehicle given the license plate
	 * @param String licensePlate licensePlate of the car to be selected. 
	 * @return CatalogDetails details of the vehicle requested.
	 */


	public CatalogDetails selectVehicle(String licensePlate) throws SQLException, ClassNotFoundException {
		ResultSet rs=vehicleRecordTableDataGateway.selectVehicle(licensePlate);
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
	 * Method to update data into the VehicleDatabase
	 * @param String type type of the car
	 * @param String model model of the car
	 * @param String make make model of the car
	 * @param int year year model of the car
	 * @param String color color model of the car
	 * @param String licensePlate licensePlate of the car
	 * @param int availability availability of the car
	 * @param int cost cost of the car per day
	 * @param int version current version of the record. 
	 */
	
	public boolean updateVehicle(String type, String make, String model, String year, String color, String licensePlate,
			String availability, int cost, int vehicleId, int version) throws ClassNotFoundException, SQLException {
		CatalogDetails newVehicle = new CatalogDetails();
		
		System.out.println("update   Vehicles:Start");
		newVehicle.setType(type);
		newVehicle.setMake(make);
		newVehicle.setModel(model);
		newVehicle.setYear(Integer.parseInt(year));
		newVehicle.setColor(color);
		newVehicle.setLicensePlate(licensePlate);
		newVehicle.setAvailability(availability);
		newVehicle.setCost(cost);
		newVehicle.setVersion(version+1);
		newVehicle.setVehicleId(vehicleId);
		boolean updateVehicleCheck=vehicleRecordTableDataGateway.updateVehicle(newVehicle,version);
		return updateVehicleCheck;
		
		
	}

	/**
	 * Method to delete a record in table given the licensePlate.
	 *@param String licensePlate licensePlate of the vehicle to be deleted.
	 *
	 */
	
	public void delete(String licensePlate) throws ClassNotFoundException, SQLException {
		vehicleRecordTableDataGateway.delete(licensePlate);
		
	}
	
	/**
	 * Method to get rented vehicle records given the licenseNumber
	 * @return List<Transaction> list of related transaction.
	 */

	
	public List<Transaction> getVehicleRentRecordsForVehicle(String licenseNumber) throws SQLException, ClassNotFoundException {
		ResultSet rs=vehicleRecordTableDataGateway.getVehicleRentRecordsForVehicle(licenseNumber);
		
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
