package com.concordia.soen.sdm.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.concordia.soen.sdm.pojo.CatalogDetails;
import com.concordia.soen.sdm.pojo.Transaction;
import com.concordia.soen.sdm.tableDataGateway.ClerkCatalogTableDataGateway;

/**
 * 
 * Mapper class for clerkCatalog
 *
 */
public class ClerkCatalogMapper {
	@Autowired
	ClerkCatalogTableDataGateway clerkCatalogTableDataGateway;
	/**
	 * THis method will read a record from database
	 * @param vehicleid,type,make,model,year,color,licenseplate,availability,cost,version 
	 *  
	 *
	 */
	public List<CatalogDetails> selectAllRecord() throws ClassNotFoundException, SQLException {
		ResultSet rs=clerkCatalogTableDataGateway.selectMultipleRows();
		List<CatalogDetails> catalogDetailsList =new ArrayList<CatalogDetails>();
		
		while(rs.next()){
			CatalogDetails catalogDetails = new CatalogDetails();
	
			catalogDetails.setVehicleId(rs.getInt("vehicleId"));
			catalogDetails.setType(rs.getString("type"));
			catalogDetails.setMake(rs.getString("make"));
			catalogDetails.setModel(rs.getString("model"));
			catalogDetails.setYear(rs.getInt("year"));
			catalogDetails.setColor(rs.getString("color"));
			catalogDetails.setLicensePlate(rs.getString("licensePlate"));
			catalogDetails.setAvailability(rs.getString("availability"));
			catalogDetails.setCost(rs.getInt("cost"));
			catalogDetails.setVersion(rs.getInt("version"));
			catalogDetailsList.add(catalogDetails);
			
		}
		
		
		return  catalogDetailsList;
	}
}
