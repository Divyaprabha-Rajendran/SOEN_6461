package com.concordia.soen.sdm.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.concordia.soen.sdm.dao.CatalogDAO;
import com.concordia.soen.sdm.dao.ClientDAO;
import com.concordia.soen.sdm.dao.TransactionDAO;
import com.concordia.soen.sdm.pojo.CatalogDetails;
import com.concordia.soen.sdm.pojo.Client;
import com.concordia.soen.sdm.pojo.Transaction;

@Controller
@RequestMapping("/reservation/*")
public class VehicleReservationController {

	@Autowired
	CatalogDAO catalogDao;

	@Autowired
	ClientDAO clientDao;
	
	@Autowired
	TransactionDAO transactionDao;

	@RequestMapping(value="/reserve")
	public ModelAndView reserve(HttpServletRequest request) throws Exception {
		ModelAndView view = new ModelAndView("vehicle_reservation");

		if(request.getParameter("startDate") != null) {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); // your template here
			
			Transaction transaction = new Transaction();
			transaction.setLicensePlate(request.getParameter("licensePlate"));
			transaction.setLicenseNumber(request.getParameter("licenseNumber"));
			String date = request.getParameter("endDate");
			transaction.setDuedate(new java.sql.Date(formatter.parse(date).getTime()));
			date = request.getParameter("startDate");
			transaction.setStartdate(new java.sql.Date(formatter.parse(date).getTime()));
			transaction.setStatus(findStatus(date));
			transaction.setCost(Integer.parseInt(request.getParameter("cost")));
			catalogDao.updateAvailability("NO", request.getParameter("licensePlate"));
			transactionDao.insertData(transaction);
			view.addObject("message", "Successfully Stored");
			
		}else {
			CatalogDetails vehicleDetails = catalogDao.getVehicleDetails(request.getParameter("licensePlate"));
			view.addObject("vehicleDetails", vehicleDetails);
			Map<String, String> userData = new HashMap<String, String>();
			List<Client> clientDetails = clientDao.getAllClientDetails();
			clientDetails.stream().forEach(data -> {
				String values = data.getFirstName()+","+data.getLastName()+","+data.getPhoneNo();
				userData.put(data.getLicenseNumber(), values);
			});
			view.addObject("user", userData);
		}
		return view;
	}

	private String findStatus(String startDate) throws ParseException {
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date std = format.parse(startDate);
		if (std.compareTo(date) == 0) {
			return "rental";
		}else {
			return "reserved";
		}
	}
}
