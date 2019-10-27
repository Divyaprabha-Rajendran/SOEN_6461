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
import com.concordia.soen.sdm.dao.VehicleDao;
import com.concordia.soen.sdm.pojo.CatalogDetails;
import com.concordia.soen.sdm.pojo.Client;
import com.concordia.soen.sdm.pojo.Transaction;
import com.concordia.soen.sdm.pojo.VehicleDetails;

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
	@Autowired
	VehicleDao vehicleDao;
	
	
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
	@RequestMapping(value="/modifyDeleteVehicle")
	public ModelAndView modifyDeleteVehicle() {
		ModelAndView view = new ModelAndView("modify_delete_vehicles");
		view.addObject("vehicleDetails", vehicleDao.getAllVehicles());
		return view;
	}
	@RequestMapping(value="/modify")
	public ModelAndView modify(HttpServletRequest request) {
		ModelAndView view = new ModelAndView("modify_vehicle");
		String licensePlate = request.getParameter("licensePlate");
		List<VehicleDetails> vehicleDetails = vehicleDao.getVechicleDetail(licensePlate);
		view.addObject("vehicleDetails", vehicleDetails.get(0));
		return view;
		
	}
	@RequestMapping(value="/delete")
	public ModelAndView delete(HttpServletRequest request) {
		ModelAndView view = new ModelAndView();
		view.setViewName("redirect:/vehicle/modifyDeleteVehicle");
		String licensePlate = request.getParameter("licensePlate");
		vehicleDao.deleteVehicle(licensePlate);
		return view;
	}
	
	@RequestMapping(value="/updateVehicleDetails")
	public ModelAndView updateVehicleDetails(HttpServletRequest request) {
		ModelAndView view = new ModelAndView("redirect:/vehicle/modifyDeleteVehicle");
		VehicleDetails details = new VehicleDetails();
		details.setAvailability(request.getParameter("availability"));
		details.setColor(request.getParameter("color"));
		details.setCost(Integer.parseInt(request.getParameter("cost")));
		details.setLicensePlate(request.getParameter("licensePlate"));
		details.setMake(request.getParameter("make"));
		details.setModel(request.getParameter("model"));
		details.setType(request.getParameter("type"));
		details.setVehicleId(Integer.parseInt(request.getParameter("vehicleId")));
		details.setYear(request.getParameter("year").trim());
		vehicleDao.updateRecord(details);
		return view;
	}
	

	@RequestMapping(value="/createNewVehicle")
	public ModelAndView createNewVehicle(HttpServletRequest request) throws Exception {
		ModelAndView view = new ModelAndView("CreateNewVehicle");
		CatalogDetails newVehicle = new CatalogDetails();
		String message ="";
		try {
			newVehicle.setType(request.getParameter("type").toLowerCase());
			newVehicle.setMake(Integer.parseInt(request.getParameter("make").trim()));
			newVehicle.setModel(Integer.parseInt(request.getParameter("model").trim()));
			newVehicle.setYear(Integer.parseInt(request.getParameter("year").trim()));
			newVehicle.setColor(request.getParameter("color").trim().toLowerCase());
			newVehicle.setLicensePlate(request.getParameter("licenseNumber").trim());
			newVehicle.setAvailability("YES");
			if(Integer.parseInt(request.getParameter("cost".trim()))>0)
			{
				//System.out.println(Integer.parseInt(request.getParameter("cost")));
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
