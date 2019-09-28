package com.concordia.soen.sdm.controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.concordia.soen.sdm.dao.ClerkLoginDAO;
import com.concordia.soen.sdm.dao.UserLoginDAO;
import com.concordia.soen.sdm.pojo.UserLogin;

@Controller
@RequestMapping("/clerk/*")
public class LoginController {

	@Autowired
	 UserLoginDAO  userLoginDAO;
	 @Autowired 
	 private HttpSession httpSession;
	@RequestMapping(value ="/clerk/login", method = RequestMethod.POST)
	public ModelAndView login(HttpServletRequest request,
			   HttpServletResponse response) {
		
		System.out.println("LoginController:Start");
		String userName=request.getParameter("username");  
	      String password=request.getParameter("password");
	      System.out.println("control:Start");
	      UserLogin user =  userLoginDAO.userLogin(userName,password);
	      httpSession=request.getSession();
	      httpSession.setAttribute("clerkName", user.getUserName());
	      //System.out.println("username:"+user.getUserId());
	     // System.out.println("control:Stop");
	      String msg;
	      msg = "Welcome" +userName + ".";
	      ModelAndView mv=new ModelAndView();
	      mv.setViewName("welcome");
	      mv.addObject("message",msg);
	      System.out.println("LoginController:Stop");
	      return mv;
	}
}

