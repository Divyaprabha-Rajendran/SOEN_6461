package com.concordia.soen.sdm.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.concordia.soen.sdm.mapper.ClientManagementMapper;
import com.concordia.soen.sdm.pojo.Client;
import com.concordia.soen.sdm.pojo.Transaction;

@Controller
@RequestMapping("/client/*")
/**
 * 
 * Controller for managing client record.
 *
 */
public class ClientManagementController {

	

	@Autowired
	ClientManagementMapper clientManagementMapper;

	@Autowired
	private HttpSession httpSession;
	
	/**
	 * THis method dashboard takes request and response as arguments. This is for displaying and searching the client details 
	 * @param request is of type HttpServeltRequest and functions like basic doget  inbuilt method 
	 * @param response is of type httpserveltResponse and functions like basic dopost 
	 * @return clientDashboard jsp is returned with results 
	 */

	@RequestMapping(value = "/client/dashboard", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView dashboard(HttpServletRequest request, HttpServletResponse response) {
		String clientId = request.getParameter("searchId");
		List<Client> clientDetailsList = null;
		ModelAndView mv = new ModelAndView();
		if (clientId == null) {
			try {
				clientDetailsList = clientManagementMapper.selectMultipleRows();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			clientDetailsList = new ArrayList<Client>();
			Client client;
			try {
				client =clientManagementMapper.select(clientId); 
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
	 * THis method is to create a new client record
	 * @param request is of type HttpServeltRequest and functions like basic doget  inbuilt method 
	 * @param response is of type httpserveltResponse and functions like basic dopost 
	 * @return add_client jsp is returned 
	 */
	@RequestMapping(value = "/client/create")
	public ModelAndView create(HttpServletRequest request, HttpServletResponse response) {
		String message = "";
		Client client = new Client();
		if (!request.getParameterMap().isEmpty()) {
			try {
				String firstName=request.getParameter("firstName");
				String lastName=request.getParameter("lastName");
				String licenseNumber=request.getParameter("licenseNumber");
				String phoneNo=request.getParameter("phone");
				String date = request.getParameter("expDate");
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				java.util.Date dateStr = formatter.parse(date);
				java.sql.Date dateDB = new java.sql.Date(dateStr.getTime());
				clientManagementMapper.insert(firstName,lastName,licenseNumber,phoneNo,dateDB);
				message = "Successfully Stored data";
			} catch (DuplicateKeyException e1) {
				message = "License number already exists. Please try again.";
				
		    }catch (Exception e) {
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
  * this method is to view and modify  the client details 
  * @param request is of type HttpServeltRequest and functions like basic doget  inbuilt method 
  * @param response is of type httpserveltResponse and functions like basic dopost 
  * @return view_client_detail is returned 
  */
	@RequestMapping(value = "/client/viewDetails", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView viewDetails(HttpServletRequest request) {
		String licenseNumber = request.getParameter("licenseNumber");
		Client client = new Client();
		String message = "";
		ModelAndView view = new ModelAndView("view_client_detail");
		if (request.getParameter("firstName") != null) {
			try {

				String firstName=request.getParameter("firstName");
				String lastName=request.getParameter("lastName");
				int version=(int) httpSession.getAttribute("recordVersion");
				String phoneNo=request.getParameter("phone");
				String date = request.getParameter("expDate");
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				java.util.Date dateStr = formatter.parse(date);
				java.sql.Date dateDB = new java.sql.Date(dateStr.getTime());
			boolean updateCheck=	clientManagementMapper.update(firstName,lastName,licenseNumber,phoneNo,dateDB,version);
				
			if(updateCheck==true) {
			message = "Successfully saved Data ";
			}
			else {
				message = "Please try again. Unknow Error Occurred";	
			}
			} catch (Exception e) {
				message = "Please try again";
				e.printStackTrace();
			}
		} else {
			try {
			
				client =clientManagementMapper.select(licenseNumber);
				httpSession.setAttribute("recordVersion", client.getVersion());
				//client = clientDAO.getClientDetails(licenseNumber);
				view.addObject("client", client);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		view.addObject("message", message);
		return view;
	}

	/**
	 * This method deleteClient is to delete the client information and it deletes by taking license number as an argument because it is unique
	 *
	 * @param request is of type httpservletrequest 
	 * @return view after deleting the client details 
	 */
	@RequestMapping(value="/deleteClient")
	public ModelAndView deleteClient(HttpServletRequest request) {
		ModelAndView view = new ModelAndView();
		String licenseNumber = request.getParameter("licenseNumber");
		String message = "";
		if(licenseNumber != null) {
			try {
				List<Transaction> transactionlist = clientManagementMapper.getVehicleRentRecordsForUsers(licenseNumber);
				if(transactionlist.size()>0)
				{
					message = "Cannot delete";
					view.setViewName("view_client_detail");
					view.addObject("message", message);
				}
				else
				{
					clientManagementMapper.delete(licenseNumber);
				}
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

