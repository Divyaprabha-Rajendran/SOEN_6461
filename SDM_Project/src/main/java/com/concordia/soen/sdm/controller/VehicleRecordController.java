package com.concordia.soen.sdm.controller;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
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
@RequestMapping("/vehicle/*")
/**
 * @author Divyaprabha Rajendran
 * Controller for managing vehicle records.
 *
 */
public class VehicleRecordController 
{
	@Autowired
	CatalogDAO catalogDao;
	
	/**
	 * Method to redirect control from welcomeAdmin.jsp to vehicleDashboard.jsp
	 * @param HTTPRequest for the corresponding page
	 * @return view for vehicleDashboard
	 */

	@RequestMapping(value="/dashboard")
	public ModelAndView dashboard(HttpServletRequest request) throws Exception {
		ModelAndView view = new ModelAndView("VehicleDashboard");
		return view;
	}
	
	/**
	 * Method to redirect control from vehicleDashboard.jsp to createNewVehicle.jsp
	 * @param HTTPRequest for the corresponding page
	 * @return view for createNewVehicle
	 */

	@RequestMapping(value="/createVehicle")
	public ModelAndView createVehicle(HttpServletRequest request) throws Exception {
		ModelAndView view = new ModelAndView("CreateNewVehicle");
		view.addObject("message", "");
		return view;
	}
	
	
	/**
	 * Method to create a new vehicle record
	 * @param HTTPRequest containing the data for the new vehicle
	 * @return view for createNewVehicle with result message
	 * @throws DuplicateKeyException when there is a duplication in licensePlate
	 * @throws Exception To handle any unforeseen exceptions.
	 */

	@RequestMapping(value="/createNewVehicle")
	public ModelAndView createNewVehicle(HttpServletRequest request) throws Exception {
		ModelAndView view = new ModelAndView("CreateNewVehicle");
		CatalogDetails newVehicle = new CatalogDetails();
		String message ="";
		try {
			newVehicle.setType(request.getParameter("type"));
			newVehicle.setMake(Integer.parseInt(request.getParameter("make")));
			newVehicle.setModel(Integer.parseInt(request.getParameter("model")));
			newVehicle.setYear(Integer.parseInt(request.getParameter("year")));
			newVehicle.setColor(request.getParameter("color"));
			newVehicle.setLicensePlate(request.getParameter("licenseNumber"));
			newVehicle.setAvailability("YES");
			if(Integer.parseInt(request.getParameter("cost"))>0)
			{
				System.out.println(Integer.parseInt(request.getParameter("cost")));
				newVehicle.setCost(Integer.parseInt(request.getParameter("cost").trim()));
				catalogDao.insertVehicleDetails(newVehicle);
				message="vehicle created successful";
				view.addObject("message", message);
			}
			else
			{
				message = "Invalid cost. Please try again.";
				view.addObject("message", message);
			}
			
		}
		catch (DuplicateKeyException e1) {
			message = "The entered license plate "+request.getParameter("licenseNumber") +" already exists. Please try again.";
			view.addObject("message", message);
	    }
		catch(Exception e)
		{
			message = "please try again";
			view.addObject("message", message);
			e.printStackTrace();
		}
		return view;
	}

	
}
