package com.concordia.soen.sdm.controller;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.concordia.soen.sdm.mapper.ClientManagementMapper;
import com.concordia.soen.sdm.mapper.LoginMapper;
import com.concordia.soen.sdm.pojo.UserLogin;

@Controller
@RequestMapping("/clerk/*")
/**
 * 
 * Controller for managing login
 *
 */
public class LoginController {

	@Autowired
	private HttpSession httpSession;
	
	@Autowired
	LoginMapper loginMapper;
	/**
	 * This method is for the clerk login. The clerk gets logged in by entering username and password
	 * @param request
	 * @param response of type httpservlet response
	 * @return
	 */

	@RequestMapping(value ="/clerk/login", method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView login(HttpServletRequest request,
			HttpServletResponse response) {
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
			String msg = "Welcome " +userName + ".";
			mv.addObject("message",msg);

		}else {
			System.out.println("reroute login method else");
			String Name=request.getParameter("username");  
			String password=request.getParameter("password");
			try {
				UserLogin user=null;
				try {
					user = loginMapper.userLogin(Name,password);
					httpSession.setAttribute("userRole", user.getRole());
					httpSession.setAttribute("userName", user.getUserName());
					userName=user.getUserName();
					if(user.getRole().equalsIgnoreCase("clerk")) {
						mv.setViewName("welcome");		

					}else if(user.getRole().equalsIgnoreCase("admin")) {
						mv.setViewName("welcomeAdmin");
					}
					String msg = "Welcome " +userName + ".";
					mv.addObject("message",msg);
					System.out.println("LoginController:Stop");
				} catch (ClassNotFoundException e) {
					mv.setViewName("redirect:/");
				} catch (SQLException e) {
					mv.setViewName("redirect:/");
				}
			
			}catch (EmptyResultDataAccessException e) {
				mv.setViewName("redirect:/");
			}catch(Exception e) {
				mv.setViewName("redirect:/");
			}
		}

		return mv;
	}
	@RequestMapping(value ="/clerk/logout", method = {RequestMethod.POST, RequestMethod.GET})
	  public String logout(HttpSession session) {
        httpSession.invalidate();
        return "redirect:/";
      }
	}

