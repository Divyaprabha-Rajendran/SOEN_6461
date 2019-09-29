package com.concordia.soen.sdm.controller;
import java.util.*;
import com.concordia.soen.sdm.pojo.CatalogDetails;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.concordia.soen.sdm.dao.CatalogDAO;



@Controller
@RequestMapping("/system/*")
public class CatalogController {
	@Autowired
	CatalogDAO CatalogDAO;
	
	@RequestMapping(value ="/system/catalog", method = RequestMethod.GET)
	public ModelAndView login(HttpServletRequest request,
			   HttpServletResponse response) {
		
		System.out.println("LoginController:Start");
		String userName=request.getParameter("username");  
	      String password=request.getParameter("password");
	      System.out.println("control:Start");
	      
	      List<CatalogDetails> cl=CatalogDAO.all();
	      for(CatalogDetails c:cl) {
	      System.out.println(c.getvehicleid()+c.getcolor()+c.getlicenseplate()+" "+c.getcost()+" "+c.getavailability()+c.getmake()+c.getmodel()+c.getyear()+c.gettype());}
	      ModelAndView mv=new ModelAndView();
	      mv.setViewName("Catalog");
	      mv.addObject("message",cl);
	      String msg;
	      msg = "Welcome" +userName + ".";
	     
	      
	      System.out.println("LoginController:Stop");
	      return mv;

}
	}
