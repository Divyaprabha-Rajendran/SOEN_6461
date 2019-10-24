package com.concordia.soen.sdm.controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.concordia.soen.sdm.dao.UserLoginDAO;
import com.concordia.soen.sdm.pojo.UserLogin;

@Controller
@RequestMapping("/clerk/*")
public class LoginController {

@Autowired
UserLoginDAO  userLoginDAO;
@Autowired
private HttpSession httpSession;
/**
* This method is for the clerk login. The clerk gets logged in by entering username and password
* @param request
* @param response of type httpservlet response
* @return
*/

@RequestMapping(value ="/clerk/login", method = {RequestMethod.POST, RequestMethod.GET})
public ModelAndView login(HttpServletRequest request,
		HttpServletResponse response) {
//	System.out.println(httpSession.getAttribute("clerkName"));
	System.out.println("reroute login method");
	String userName = null;
	ModelAndView mv=new ModelAndView();
	if(httpSession.getAttribute("userRole") != null) {
		String role=(String) httpSession.getAttribute("userRole");
		userName = (String) httpSession.getAttribute("userName");
		if(role.equalsIgnoreCase("clerk")) {
			mv.setViewName("welcome");	
			
		}else if(role.equalsIgnoreCase("admin")) {
			mv.setViewName("welcomeAdmin");	
				
		}
		
	}else {
		System.out.println("reroute login method else");
		String Name=request.getParameter("username");  
		String password=request.getParameter("password");
		try {
			UserLogin user =  userLoginDAO.userLogin(Name,password);
			httpSession.setAttribute("userRole", user.getRole());
			httpSession.setAttribute("userName", user.getUserName());
			userName=user.getUserName();
			if(user.getRole().equalsIgnoreCase("clerk")) {
				mv.setViewName("welcome");		
				
			}else if(user.getRole().equalsIgnoreCase("admin")) {
				mv.setViewName("welcomeAdmin");
			}
			
			System.out.println("LoginController:Stop");
		}catch (EmptyResultDataAccessException e) {
			mv.setViewName("redirect:/");
		}
	}
	if(userName.length()>0) {
	String msg = "Welcome " +userName + ".";
	mv.addObject("message",msg);
	}
	return mv;
}


}