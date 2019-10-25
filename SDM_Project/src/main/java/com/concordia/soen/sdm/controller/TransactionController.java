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


@Controller
@RequestMapping("/transaction/*")
public class TransactionController {
	
	@Autowired
	TransactionDAO transactionDao;
	
	@Autowired
	HttpSession httpSession;
	
	@RequestMapping(value = "/transaction/transactionDetails", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView getTransactionDetails(HttpServletRequest request, HttpServletResponse response) {
		List<Transaction> transactionDetailList = null;
		transactionDetailList=transactionDao.getAllTransactions();
		ModelAndView mv = new ModelAndView();
		mv.setViewName("transaction_details_view");
		mv.addObject("transactionDetails", transactionDetailList);
		return mv;
		
	}
	
	

}
