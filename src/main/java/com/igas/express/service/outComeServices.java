package com.igas.express.service;

import org.springframework.stereotype.Service;

import com.igas.express.model.entitiy.outCome;
import com.igas.express.util.ResponseObject;
@Service
public interface outComeServices {
	
public ResponseObject createOutCome(outCome outcome);
	
	public ResponseObject updateOutCome(long id , outCome outcome);
	
	
	public ResponseObject getAllOutCome() ;

	public ResponseObject getAllOutComeFlight( String startDate, String endDate);

	public ResponseObject getOutComeId(long id); 

}
