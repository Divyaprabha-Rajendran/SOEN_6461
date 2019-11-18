package com.concordia.soen.sdm.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.concordia.soen.sdm.dao.CatalogDAO;
import com.concordia.soen.sdm.dao.TransactionDAO;
import com.concordia.soen.sdm.mapper.CancelReturnMapper;
//import com.concordia.soen.sdm.dao.TransactionsaveDAO;
import com.concordia.soen.sdm.pojo.CancelReturn;
import com.concordia.soen.sdm.pojo.CatalogDetails;
import com.concordia.soen.sdm.pojo.Transaction;

@Controller
@RequestMapping("/cancelReturn/*")
public class CancelReturnController {

	
	@Autowired
	CancelReturnMapper cancelReturnMapper;
	
	
	//TransactionsaveDAO savereturn;
	Transaction transaction;

	@Autowired 
	private HttpSession httpSession;
	/**
	 * This method is for the transaction details. Here we are getting transaction details from database
	 * @param request is of type HTTPServletRequest 
	 * @param response is of type HTTPServletResponse 
	 * @return view is returned which is of type ModelandView
	 */

	@RequestMapping(value ="/cancelReturn/transactionSearch", method =RequestMethod.GET)
	public ModelAndView transactionSearch(HttpServletRequest request,
			HttpServletResponse response) {
		httpSession=request.getSession();
		List<CancelReturn> vehicleDetails =null;
		if(request.getParameter("licensePlate") != null) {
			try {
			String licensePlate = request.getParameter("licensePlate"); 
			String operation = request.getParameter("operation"); 
			String reservationId=request.getParameter("reservationId");
			System.out.println("Reservation Id:"+reservationId);
			System.out.println("License Plate:"+licensePlate);
			System.out.println("Operation:"+operation);
			if(operation.equalsIgnoreCase("reserved")) {
			
					cancelReturnMapper.cancel(reservationId);
				
			}else if(operation.equalsIgnoreCase("rented")) {
				cancelReturnMapper.vehicleReturn(licensePlate,reservationId);
			}
			}
		 catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println("done");
		}
		 try {
			vehicleDetails	= cancelReturnMapper.getRentedVehicles();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ModelAndView mv=new ModelAndView("CancelDetail");
		mv.addObject("vehicleDetails", vehicleDetails);
		return mv;
	}

}
