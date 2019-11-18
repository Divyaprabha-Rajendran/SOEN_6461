package com.concordia.soen.sdm.service;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.concordia.soen.sdm.pojo.Transaction;
/**
 * 
 * This service class is to check the availability of vehicle on particular date 
 *
 */
public class TransactionAvailabilityCheckService {
	/**
	 * This method is for checking the availability
	 * @param transaction details list  
	 * @param availability checking date
	 * @return  string available or not available
	 */
	public String availabilityCheck(List<Transaction> transactionDetailList, Date dateStr) throws ParseException {
		
			
		String dateFormat="yyyy/MM/dd";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		sdf.setLenient(false);
		for(Transaction transaction :transactionDetailList) {
			
			
			Date begindate = null;
			Date returndate = null ;
			
				begindate = sdf.parse(transaction.getStartdate());
				returndate = sdf.parse(transaction.getDuedate());
			
				if(!(dateStr.before(begindate)  || dateStr.after(returndate) )){
					return "not Available";
				}
				
			
		}
		
		return "available";
		
		
		
	}
}
