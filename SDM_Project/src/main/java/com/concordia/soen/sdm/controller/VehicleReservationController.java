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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.concordia.soen.sdm.dao.CatalogDAO;
import com.concordia.soen.sdm.dao.ClientDAO;
import com.concordia.soen.sdm.dao.TransactionDAO;
import com.concordia.soen.sdm.pojo.CatalogDetails;
import com.concordia.soen.sdm.pojo.Client;
import com.concordia.soen.sdm.pojo.Transaction;

/**
 * @author Divyaprabha Rajendran
 * Controller for making reservation or rental.
 *
 */
@Controller
@RequestMapping("/reservation/*")
public class VehicleReservationController {

	@Autowired
	CatalogDAO catalogDao;

	@Autowired
	ClientDAO clientDao;
	
	@Autowired
	TransactionDAO transactionDao;
	
	/**
	 * Method to process the reservation or rental request
	 * @param HTTPRequest containing the license plate  and license number  
	 * @return  vehicle_reservation view to show the reservation details.
	 * @throws Exception To handle all exceptions.
	 */

	@RequestMapping(value="/reserve")
	public ModelAndView reserve(HttpServletRequest request) throws Exception {
		ModelAndView view = new ModelAndView("vehicle_reservation");

		if(request.getParameter("startDate") != null) {
			SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // your template here
			
			Transaction transaction = new Transaction();
			transaction.setLicensePlate(request.getParameter("licensePlate"));
			transaction.setLicenseNumber(request.getParameter("licenseNumber"));
			String date = request.getParameter("endDate");
			date = date.replace("T", " ");
			String dateTime = formater.format(formater.parse(date));
			transaction.setDuedate(dateTime);
			date = request.getParameter("startDate");
			date = date.replace("T", " ");
			dateTime = formater.format(formater.parse(date));
			transaction.setStartdate(dateTime);
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
	
	@ResponseBody
	@RequestMapping(value="/checkVehicleAvailability", method= {RequestMethod.POST, RequestMethod.GET})
	public String checkVehicleAvailability(HttpServletRequest request) throws ParseException {
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String numberPlate = request.getParameter("numberPlate");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date sDate = format.parse(startDate);
		Date eDate = format.parse(endDate);
		List<Transaction> records = transactionDao.getVehicleRentRecords(numberPlate);
		for(int i = 0; i < records.size(); i++) {
			Transaction data = records.get(i);
			Date dbStartDate = format.parse(data.getStartdate());
			Date dbEndDate = format.parse(data.getDuedate());
			System.out.println(data.getStartdate()+" "+sDate+" "+endDate);
			if(dbStartDate.compareTo(sDate) < 0 && dbEndDate.compareTo(sDate) > 0) {
				return new String("FALSE");
			}
			else if(dbStartDate.compareTo(eDate) > 0 && dbEndDate.compareTo(eDate) < 0) {
				return new String("FALSE");
			}else if(dbStartDate.compareTo(sDate) < 0 && dbEndDate.compareTo(eDate) > 0) {
				return new String("FALSE");
			}
		}
		return new String("TRUE");
	}


	/**
	 * Method to find reservation or rent
	 * @param startDate To evaluate the status  
	 * @return  status can be rental/reservation
	 */
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
