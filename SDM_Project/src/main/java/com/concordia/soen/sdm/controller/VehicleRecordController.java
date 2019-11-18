package com.concordia.soen.sdm.controller;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.concordia.soen.sdm.dao.CatalogDAO;
import com.concordia.soen.sdm.dao.ClientDAO;
import com.concordia.soen.sdm.dao.TransactionDAO;
import com.concordia.soen.sdm.dao.VehicleDao;
import com.concordia.soen.sdm.mapper.ClientManagementMapper;
import com.concordia.soen.sdm.mapper.VehicleRecordMapper;
import com.concordia.soen.sdm.pojo.CatalogDetails;
import com.concordia.soen.sdm.pojo.Client;
import com.concordia.soen.sdm.pojo.Transaction;
import com.concordia.soen.sdm.pojo.VehicleDetails;

@Controller
@RequestMapping("/vehicle/*")
/**
 * 
 * Controller for managing vehicle records.
 *
 */
public class VehicleRecordController 
{
	@Autowired
	CatalogDAO catalogDao;
	@Autowired
	VehicleDao vehicleDao;
	@Autowired
	VehicleRecordMapper vehicleRecordMapper;
	@Autowired
	private HttpSession httpSession;
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
	
	
	
	@RequestMapping(value="/delete")
	public ModelAndView delete(HttpServletRequest request) {
		ModelAndView view = new ModelAndView();
		view.setViewName("redirect:/vehicle/modifyDeleteVehicle");
		String licensePlate = request.getParameter("licensePlate");
		try {
			List<Transaction> transactionlist = vehicleRecordMapper.getVehicleRentRecordsForVehicle(licensePlate);
			if(transactionlist.size()>0)
			{
				System.out.println("Cannot delete "+licensePlate);
			}
			else
			{
			vehicleRecordMapper.delete(licensePlate);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	//	vehicleDao.deleteVehicle(licensePlate);
		return view;
	}
	
	@RequestMapping(value="/createNewVehicle")
	public ModelAndView createNewVehicle(HttpServletRequest request) throws Exception {
		ModelAndView view = new ModelAndView("CreateNewVehicle");
		CatalogDetails newVehicle = new CatalogDetails();
		String message ="";
		try {
			
			String type=request.getParameter("type").toLowerCase();
			String make=request.getParameter("make").toLowerCase();
			String model=request.getParameter("model").trim();
			int year=Integer.parseInt(request.getParameter("year").trim());
			String color=request.getParameter("color").trim().toLowerCase();
			String licensePlate=request.getParameter("licenseNumber").trim();
			String availability="YES";
			

			if(Integer.parseInt(request.getParameter("cost".trim()))>0)
			{
				//System.out.println(Integer.parseInt(request.getParameter("cost")));
				int cost=Integer.parseInt(request.getParameter("cost").trim());
				vehicleRecordMapper.insert(type,make,model,year,color,licensePlate,availability,cost);
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
