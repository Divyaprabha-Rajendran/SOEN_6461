package com.concordia.soen.sdm.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.concordia.soen.sdm.pojo.Client;
import com.concordia.soen.sdm.pojo.Transaction;
import com.concordia.soen.sdm.service.TransactionAvailabilityCheckService;


@Controller
@RequestMapping("/transaction/*")
public class TransactionController {
	
	@Autowired
	TransactionDAO transactionDao;
	@Autowired
	TransactionAvailabilityCheckService  transactionAvailabilityCheck;
	@Autowired
	HttpSession httpSession;
	/**
	 * This method is for the transaction details. Here we are getting transaction details from database
	 * @param request is of type HTTPServletRequest 
	 * @param response is of type HTTPServletResponse 
	 * @return view is returned which is of type ModelandView
	 */
	@RequestMapping(value = "/transaction/transactionDetails", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView getTransactionDetails(HttpServletRequest request, HttpServletResponse response) {
		List<Transaction> transactionDetailList = null;
		transactionDetailList=transactionDao.getAllTransactions();
		ModelAndView mv = new ModelAndView();
		mv.setViewName("transaction_details_view");
		mv.addObject("transactionDetails", transactionDetailList);
		return mv;
		
	}
	@RequestMapping(value = "/transaction/transactionFiltering")
	public ModelAndView getTransactionData(HttpServletRequest request, HttpServletResponse response) {
		List<Transaction> transactionDetailList= null;
		//transactionDetailList=transactionDao.getAllTransactions();
		ModelAndView mv = new ModelAndView();
		String CriteriaData=request.getParameter("criteriaOption");
		String SearchData=request.getParameter("searchData");
		String dateFlag=request.getParameter("vehicleDate") ;
		String availability=null;
		mv.addObject("AvailableMsg",null);
		mv.addObject("errorMsg", null);
		//System.out.println("Data Size1:"+flag);
		try {
			if((CriteriaData!=null)) {
		if(  CriteriaData.equalsIgnoreCase("userId")) {
			
			transactionDetailList=transactionDao.getUserTransactions(SearchData);
		}else if(CriteriaData.equalsIgnoreCase("vehicleId")) {
			transactionDetailList=transactionDao.getVehicleTransactions(SearchData);
			if(dateFlag !=null) {
				String dueDate=request.getParameter("Date");
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				Date dateStr = formatter.parse(dueDate);
			 availability=transactionAvailabilityCheck.availabilityCheck(transactionDetailList,dateStr);
			 
			 if(availability.equalsIgnoreCase("available")) {
				 mv.addObject("AvailableMsg","Vehicle with licenseplate "+SearchData+" is available.");
			 }else {
				 mv.addObject("AvailableMsg","Vehicle with licenseplate "+SearchData+" is not available.");
			 }
			}
			
		}else if(CriteriaData.equalsIgnoreCase("dueDateOption")) {
			String dueDate=request.getParameter("Date");
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			
			Date dateStr = formatter.parse(dueDate);
			transactionDetailList=transactionDao.getdueDateTransactions(dateStr);
		
		}else if(CriteriaData.equalsIgnoreCase("rentedVehicles")) {
			transactionDetailList=transactionDao.getrentedVehicleTransactions("rented");
		
		}
		//System.out.println("Data Size:"+transactionDetailList.size());
		
			}} catch (Exception e) {
			// TODO Auto-generated catch block
			//message = "Please try again. Unknow Error Occurred";
				mv.addObject("errorMsg", "Record is not available");
			e.printStackTrace();
		}
		
		mv.setViewName("transaction_details_view");
		mv.addObject("transactionDetails", transactionDetailList);
		return mv;
		
	}
	

}
