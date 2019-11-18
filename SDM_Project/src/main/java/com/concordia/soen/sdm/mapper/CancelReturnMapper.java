package com.concordia.soen.sdm.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.concordia.soen.sdm.pojo.CancelReturn;
import com.concordia.soen.sdm.pojo.Client;
import com.concordia.soen.sdm.pojo.Transaction;
import com.concordia.soen.sdm.tableDataGateway.CancelReturnTableDataGateway;
/**
 * 
 * Mapper class for CancelReturn
 *
 */
public class CancelReturnMapper {

	
	@Autowired
	CancelReturnTableDataGateway cancelReturnTableDataGateway;
	

	/**
	 * THis method will get rented vehicles record from database
	 * @return list of transaction
	 *  
	 *
	 */
	public List<CancelReturn> getRentedVehicles() throws SQLException, ClassNotFoundException {
		ResultSet rs=cancelReturnTableDataGateway.selectMultipleRows();
		List<CancelReturn> transactionList =new ArrayList<CancelReturn>();
		System.out.println("Cancel Return mApper:Start");
		while(rs.next()){
			CancelReturn transaction = new CancelReturn();
			
			transaction.setType(rs.getString("type"));
			transaction.setLicensePlate(rs.getString("licensePlate"));
			transaction.setAvailability(rs.getString("availability"));
			transaction.setCost(rs.getInt("cost"));
			transaction.setLicenseNumber(rs.getString("licenseNumber"));
			transaction.setStartDate(rs.getDate("startDate"));
			transaction.setDueDate(rs.getDate("dueDate"));
			transaction.setStatus(rs.getString("status"));
			transaction.setReservationId(rs.getInt("reservationId"));
			System.out.println(rs.getInt("reservationId"));
			transactionList.add(transaction);
		}
		System.out.println("Cancel Return mApper:Stop");
		return  transactionList;
	}


	/**
	 * THis method will cancel a record in database
	 * @param reservationId
	 *  
	 *
	 */
	public void cancel(String reservationId) throws ClassNotFoundException, SQLException {
		CancelReturn transaction = new CancelReturn();
		transaction.setStatus("cancelled");
		transaction.setReservationId(Integer.parseInt(reservationId));
		cancelReturnTableDataGateway.update(transaction);
		
	}
	/**
	 * This method will return a record in database
	 * @param licensePlate,reservationId
	 *  
	 *
	 */

	public void vehicleReturn(String licensePlate, String reservationId) throws ClassNotFoundException, SQLException {
		CancelReturn transaction = new CancelReturn();
		transaction.setAvailability("YES");
		transaction.setStatus("returned");
		transaction.setLicensePlate(licensePlate);
		transaction.setReservationId(Integer.parseInt(reservationId));
		cancelReturnTableDataGateway.updateReturn(transaction);
	}

}
