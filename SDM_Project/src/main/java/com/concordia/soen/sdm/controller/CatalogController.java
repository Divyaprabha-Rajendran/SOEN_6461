
package com.concordia.soen.sdm.controller;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.concordia.soen.sdm.dao.CatalogDAO;
import com.concordia.soen.sdm.pojo.CatalogDetails;
/** 
 * CatalogController has two methods
 * @method1: "catalog" method helps to set the attributes and get messages on the screen
 * @method2: "viewDetails" method is used to save the transaction on page and move to next step
 * @return: returns the view
 */
@Controller
@RequestMapping("/system/")
public class CatalogController {

	@Autowired
	CatalogDAO catalogDao;
	
	@Autowired
	HttpSession httpSession;
	
	@RequestMapping(value="/catalog")
	public ModelAndView catalog(HttpServletRequest request,
			   HttpServletResponse response) {
		List<CatalogDetails> cl=catalogDao.all();
		List<String> filterKeys = (List<String>) request.getParameterMap().keySet().stream().collect(Collectors.toList());
		if(filterKeys.contains("sort"))
			filterKeys.remove("sort");
		String from  = request.getParameter("from");
		filterKeys.remove("from");
		System.out.println(from);
		System.out.println(filterKeys);
		String sortOrder = request.getParameter("sort");
		Set<String> vehicleTypeSet = cl.stream().map(data -> data.getType()).collect(Collectors.toSet());
		List<String> vehicleTypeList = vehicleTypeSet.stream().collect(Collectors.toList());
		List<CatalogDetails> filterData = cl.stream().filter(data -> filterKeys.isEmpty() || filterKeys.contains(data.getType())).collect(Collectors.toList());
		if(sortOrder != null) {
			filterData = sortData(filterData, sortOrder);
		}
		System.out.println(filterData);
		httpSession.setAttribute("filterData", filterData);
		ModelAndView view = new ModelAndView("Catalog");
		view.addObject("message",filterData);
		view.addObject("types", vehicleTypeList);
		view.addObject("from", from);
		return view;
	}

	private List<CatalogDetails> sortData(List<CatalogDetails> filterData, String sortOrder) {
		if(sortOrder.equals("year")) {
			return filterData.stream().sorted((a,b) ->a.getYear() > b.getYear() ? -1:1).collect(Collectors.toList());
		}else {
			return filterData.stream().sorted((a,b) -> a.getType().compareTo(b.getType())).collect(Collectors.toList());
		}
	}
	
	@RequestMapping(value = "/catalog/viewDetail")
	public ModelAndView viewDetail(HttpServletRequest request) {
		System.out.println(request.getParameterMap());
		System.out.println(request.getParameter("idparam"));
		int idParam = Integer.parseInt(request.getParameter("idparam"));
		System.out.println(idParam);
//		
		List<CatalogDetails> filterData = (List<CatalogDetails>) httpSession.getAttribute("filterData");
		filterData = filterData.stream().sorted((a,b) -> a.getVehicleId()).collect(Collectors.toList());
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
