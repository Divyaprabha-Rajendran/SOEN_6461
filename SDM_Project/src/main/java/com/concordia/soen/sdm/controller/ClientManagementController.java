
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

/**
 * @author Harish Jayasankar
 * Controller for Client management system.
 *
 */
@Controller
@RequestMapping("/client/*")

public class ClientManagementController {

	@Autowired
	ClientDAO clientDAO;

	@Autowired
	private HttpSession httpSession;

	/**
	 * Method to search and view all client
	 * @param HTTPRequest containing search Id  
	 * @return  client details will be passed to jsp.
	 */
	@RequestMapping(value = "/client/dashboard", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView dashboard(HttpServletRequest request, HttpServletResponse response) {
		String clientId = request.getParameter("searchId");
		System.out.println(clientId);
		List<Client> clientDetailsList = null;
		ModelAndView mv = new ModelAndView();
		if (clientId == null) {
			try {
				clientDetailsList = clientDAO.getAllClientDetails();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			clientDetailsList = new ArrayList<Client>();
			Client client;
			try {
				client = clientDAO.getClientDetails(clientId);
				clientDetailsList.add(client);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		mv.setViewName("clientDashboard");
		mv.addObject("clientDetails", clientDetailsList);
		return mv;
	}

	/**
	 * Method to create new client
	 * @param HTTPRequest containing firstname,lastname,licensenumber,expiry date,phonenumber 
	 * @return  success message will be displayed.
	 */
	@RequestMapping(value = "/client/create")
	public ModelAndView create(HttpServletRequest request, HttpServletResponse response) {
		String message = "";
		Client client = new Client();
		if (!request.getParameterMap().isEmpty()) {
			try {
				
				client.setFirstName(request.getParameter("firstName"));
				client.setLastName(request.getParameter("lastName"));
				client.setLicenseNumber(request.getParameter("licenseNumber"));

				String date = request.getParameter("expDate");
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
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
		ModelAndView view = new ModelAndView("add_client");
		view.addObject("message", message);
		return view;
	}

	
	/**
	 * Method to view and update client details
	 * @param HTTPRequest containing firstname,lastname,licensenumber,expiry date,phonenumber 
	 * @return  success message will be displayed.
	 */
	@RequestMapping(value = "/client/viewDetails", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView viewDetails(HttpServletRequest request) {
		String licenseNumber = request.getParameter("licenseNumber");
		Client client = new Client();
		String message = "";
		ModelAndView view = new ModelAndView("view_client_detail");
		if (request.getParameter("firstName") != null) {
			try {

				client.setFirstName(request.getParameter("firstName"));
				System.out.println("Checking:" + request.getParameter("firstName"));
				client.setLastName(request.getParameter("lastName"));
				client.setLicenseNumber(request.getParameter("licenseNumber"));

				String date = request.getParameter("expDate");
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				java.util.Date dateStr = formatter.parse(date);
				java.sql.Date dateDB = new java.sql.Date(dateStr.getTime());
				client.setLicenseExpiryDate(dateDB);
				client.setPhoneNo(request.getParameter("phone"));
				clientDAO.updateClientDetails(client);
				message = "Successfully saved Data ";
			} catch (Exception e) {
				message = "Please try again";
				e.printStackTrace();
			}
		} else {
			try {
				client = clientDAO.getClientDetails(licenseNumber);
				view.addObject("client", client);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		view.addObject("message", message);
		return view;
	}

	/*
	 * @RequestMapping(value ="/client/search", method ={RequestMethod.GET,
	 * RequestMethod.POST}) public ModelAndView search(HttpServletRequest request,
	 * HttpServletResponse response) { String
	 * clientId=request.getParameter("searchId"); Client client; ModelAndView mv=new
	 * ModelAndView(); try { client = clientDAO.getClientDetails(clientId);
	 * httpSession=request.getSession();
	 * httpSession.setAttribute("searchClientDetails", client);
	 * mv.setViewName("clientDashboard"); mv.addObject("client", client); } catch
	 * (Exception e) { // TODO Auto-generated catch block e.printStackTrace(); }
	 * System.out.println("search");
	 * 
	 * 
	 * 
	 * return mv; }
	 * 
	 * 
	 * @RequestMapping(value ="/client/clientDetails", method ={RequestMethod.GET,
	 * RequestMethod.POST}) public ModelAndView clientDetails(HttpServletRequest
	 * request, HttpServletResponse response) {
	 * System.out.println("rerouting to same page details"); String
	 * clientId=request.getParameter("clientID");
	 * System.out.println("clientid:"+clientId); httpSession=request.getSession();
	 * 
	 * Client client=(Client)
	 * httpSession.getAttribute("searchClientDetails");////clientDAO.
	 * getClientDetails(clientId);
	 * 
	 * ModelAndView mv=new ModelAndView(); mv.setViewName("clientDetail");
	 * mv.addObject("client", client); return mv; }
	 * 
	 * @RequestMapping(value ="/client/clientDetailEdit", method
	 * ={RequestMethod.GET, RequestMethod.POST}) public ModelAndView
	 * clientDetailEdit(HttpServletRequest request, HttpServletResponse response) {
	 * System.out.println("clientDetailsEdit");
	 * 
	 * String clientId=request.getParameter("clientId"); Client
	 * client=clientDAO.getClientDetails(clientId);
	 * 
	 * httpSession=request.getSession();
	 * 
	 * Client client=(Client) httpSession.getAttribute("searchClientDetails");
	 * ModelAndView mv=new ModelAndView(); mv.setViewName("clientDetailsEdit");
	 * mv.addObject("client", client); return mv; }
	 */
	
	/**
	 * 
	 * method deleteClient to delete a client
	 * @param request HttpServletRequest
	 * @return ModelAndView
	 */
	@RequestMapping(value="/deleteClient")
	public ModelAndView deleteClient(HttpServletRequest request) {
		ModelAndView view = new ModelAndView();
		String licenseNumber = request.getParameter("licenseNumber");
		System.out.println("came"+licenseNumber);
		String message = "";
		if(licenseNumber != null) {
			try {
				clientDAO.deleteClient(licenseNumber);
				view.setViewName("redirect:/client/dashboard");
			}catch (Exception e) {
				message = "Problem while deleting. Please try again";
				view.setViewName("view_client_detail");
				view.addObject("message", message);
				e.printStackTrace();
			}
		}
		return view;
	}

}
