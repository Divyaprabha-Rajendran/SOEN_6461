package com.concordia.soen.sdm.controller;
import java.util.*;
import com.concordia.soen.sdm.pojo.CatalogDetails;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.concordia.soen.sdm.dao.CatalogDAO;
import com.concordia.soen.sdm.pojo.CatalogDetails;


@Controller
@RequestMapping("/system/*")
public class CatalogController {
	@Autowired
	CatalogDAO CatalogDAO;
	
	@Autowired
	CatalogDAO catalogDao;
	
	@Autowired
	HttpSession httpSession;
	
	@RequestMapping(value="/catalog")
	public ModelAndView catalog(HttpServletRequest request,
			   HttpServletResponse response) {
		List<CatalogDetails> cl=catalogDao.all();
		
		httpSession.setAttribute("filterData", cl);
		ModelAndView view = new ModelAndView("Catalog");
		view.addObject("message",cl);
		System.out.print(cl);
		return view;
	}
	@RequestMapping(value = "/catalog/viewDetail")
	public ModelAndView viewDetail(HttpServletRequest request) {
		System.out.println(request.getParameterMap());
		System.out.println(request.getParameter("idparam"));
		int idParam = Integer.parseInt(request.getParameter("idparam"));
		System.out.println(idParam);

		List<CatalogDetails> filterData = (List<CatalogDetails>) httpSession.getAttribute("filterData");
		
		int index = 0;
		int nextId = 0;
		CatalogDetails transaction = null;
		for(int i = 0; i < filterData.size(); i++) {
			if(filterData.get(i).getVehicleId() == idParam) {
				transaction = filterData.get(i);
				nextId = filterData.get((i+1) % filterData.size()).getVehicleId();
				break;
			}
		}
		ModelAndView view = new ModelAndView("ViewVehicleDetails");
		view.addObject("transaction", transaction);
		view.addObject("nextId", nextId);
		return view;
	}
	}
