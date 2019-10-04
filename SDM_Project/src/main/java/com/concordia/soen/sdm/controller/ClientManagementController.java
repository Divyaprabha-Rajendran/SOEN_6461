package com.concordia.soen.sdm.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.concordia.soen.sdm.dao.ClientDAO;
import com.concordia.soen.sdm.pojo.Client;

@Controller
@RequestMapping("/client/*")

public class ClientManagementController {


	@Autowired
	ClientDAO  clientDAO;

	@Autowired 
	private HttpSession httpSession;

	@RequestMapping(value ="/client/dashboard", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView dashboard(HttpServletRequest request,
			HttpServletResponse response) {
		String clientId=request.getParameter("searchId");
		System.out.println(clientId);
		List<Client> clientDetailsList;
		ModelAndView mv=new ModelAndView();
		try {
			clientDetailsList = clientDAO.getAllClientDetails();
			mv.addObject("clientDetails", clientDetailsList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		mv.setViewName("clientDashboard");
		
		return mv;
	}



	@RequestMapping(value ="/client/create")
	public ModelAndView create(HttpServletRequest request,
			HttpServletResponse response) {
		String message = "";
		Client client = new Client();
		if(!request.getParameterMap().isEmpty()) {
			try {
				System.out.println(request.getParameterMap());
				client.setFirstName(request.getParameter("firstName"));
				client.setLastName(request.getParameter("lastName"));
				client.setLicenseNumber(request.getParameter("licenseNumber"));

				String date = request.getParameter("expDate");
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); // your template here
				java.util.Date dateStr = formatter.parse(date);
				java.sql.Date dateDB = new java.sql.Date(dateStr.getTime());
				client.setLicenseExpiryDate(dateDB);
				client.setPhoneNo(request.getParameter("phone"));
				clientDAO.insertClientDetails(client);
				message = "Successfully Stored data";
			} catch (Exception e) {
				// TODO Auto-generated catch block
				message = "Please try again. Unknow Error Occurred";
				e.printStackTrace();
			}
		}
		ModelAndView view=new ModelAndView("add_client");
		view.addObject("message", message);
		return view;
	}

	

}
