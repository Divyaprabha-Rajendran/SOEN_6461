package com.concordia.soen.sdm.controller;

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
//import com.concordia.soen.sdm.dao.TransactionsaveDAO;
import com.concordia.soen.sdm.pojo.CancelReturn;
import com.concordia.soen.sdm.pojo.CatalogDetails;
import com.concordia.soen.sdm.pojo.Transaction;

@Controller
@RequestMapping("/cancelReturn/*")
public class CancelReturnController {

	@Autowired
	CatalogDAO catalogDao;
	
	@Autowired
	TransactionDAO transactionDAO;
	//TransactionsaveDAO savereturn;
	Transaction transaction;

	@Autowired 
	private HttpSession httpSession;


	@RequestMapping(value ="/cancelReturn/transactionSearch", method =RequestMethod.GET)
	public ModelAndView transactionSearch(HttpServletRequest request,
			HttpServletResponse response) {
		httpSession=request.getSession();
		if(request.getParameter("licensePlate") != null) {
			String licensePlate = request.getParameter("licensePlate"); 
			catalogDao.updateAvailability("YES", licensePlate); 
			System.out.println("done");
		}
		List<CancelReturn> vehicleDetails = catalogDao.getRentedVehicles();
		ModelAndView mv=new ModelAndView("CancelDetail");
		mv.addObject("vehicleDetails", vehicleDetails);
		return mv;
	}
	
	

	@RequestMapping(value ="/cancelReturn/transactionDetail", method =RequestMethod.POST)
	public ModelAndView transactionDetail(HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("checking detail");
		String transactionId=request.getParameter("transactionId");  
		int reservationId=Integer.parseInt(transactionId);
		Transaction transaction=transactionDAO.getTransactionID(reservationId);
		System.out.println("transactiondetail");
		httpSession=request.getSession();
		httpSession.setAttribute("searchtransactionDetails", transaction);
		ModelAndView mv=new ModelAndView();
		mv.setViewName("CancelDetail");
		mv.addObject("transaction", transaction);
		return mv;
	}



	@RequestMapping(value ="/cancelReturn/transactionExtract",method=RequestMethod.GET) 
	public ModelAndView transactionExtract(HttpServletRequest request, HttpServletResponse response)
	{ 
		System.out.println("rerouting to same page details"); 
		String transactionId=request.getParameter("reservationId");
		System.out.println(transactionId); 
		int reservationId=Integer.parseInt(transactionId); 
		Transaction transaction=transactionDAO.getTransactionID(reservationId);
		System.out.println("transactionId:"+transactionId);
		// System.out.println(transaction.getStatus());
		httpSession=request.getSession(); 
		// Transaction transaction=(Transaction)httpSession.getAttribute("searchtransaction extract" );
		ModelAndView mv=new ModelAndView(); 
		mv.setViewName("TransactionDetail");
		mv.addObject("transaction", transaction); 

		return mv;
	}

	@RequestMapping(value ="/cancelReturn/reservedsave",method=RequestMethod.GET) 
	public ModelAndView returnsave(HttpServletRequest request, HttpServletResponse response)
	{ 
		System.out.println("saving to db"); 
		String transactionstatus=request.getParameter("status");
		String transactionId=request.getParameter("reservationId");

		int reservationId=Integer.parseInt(transactionId);
		System.out.println(reservationId); 
		Transaction transactionget=transactionDAO.getTransactionID(reservationId);
		Transaction transactionstat=transactionDAO.getTransactionStatus(transactionstatus);
		Transaction transactionsave=transactionDAO.getreturnsave(transactionstat);
		httpSession=request.getSession();
		ModelAndView mv=new ModelAndView(); 
		mv.setViewName("savereservedstatus");
		//  mv.addObject("transactionstatus", transaction); 
		System.out.println("transactionstatus:"+transactionsave);
		return mv;
	}

	@RequestMapping(value ="/cancelReturn/rentalsave",method=RequestMethod.GET) 
	public ModelAndView rentalsave(HttpServletRequest request, HttpServletResponse response)
	{ 
		System.out.println("saving return to db"); 
		String transactionstatus=request.getParameter("status");
		String transactionId=request.getParameter("reservationId");
		//System.out.println(transactionId); 
		int reservationId=Integer.parseInt(transactionId);

		Transaction transactionget=transactionDAO.getTransactionID(reservationId);
		Transaction transactionstat=transactionDAO.getTransactionStatus(transactionstatus);
		Transaction transactionreturn=transactionDAO.getreturnsave(transactionstat);
		httpSession=request.getSession();
		ModelAndView mv=new ModelAndView(); 
		mv.setViewName("Returnedsave");
		//  mv.addObject("transactionstatus", transactionreturn); 
		System.out.println("transactionstatus:"+transactionreturn);
		return mv;
	}
}
