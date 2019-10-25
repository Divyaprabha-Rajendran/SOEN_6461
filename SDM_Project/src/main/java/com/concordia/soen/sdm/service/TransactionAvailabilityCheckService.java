package com.concordia.soen.sdm.service;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.concordia.soen.sdm.pojo.Transaction;

public class TransactionAvailabilityCheckService {

	public String availabilityCheck(List<Transaction> transactionDetailList, Date dateStr) throws ParseException {
		
			
		String dateFormat="yyyy/MM/dd";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
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
