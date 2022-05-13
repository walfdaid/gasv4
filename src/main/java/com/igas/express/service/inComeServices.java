package com.igas.express.service;

import org.springframework.stereotype.Service;

import com.igas.express.model.entitiy.InCome;
import com.igas.express.util.ResponseObject;
@Service
public interface inComeServices {
	
	
	public ResponseObject createInCome(InCome Income);
	
	public ResponseObject updateInCome(long id , InCome Income);
	
	public ResponseObject getAllInCome() ;

	public ResponseObject getAllInComeFlight(String startDate, String endDate);

	public ResponseObject getIncomeById(long id); 


}
