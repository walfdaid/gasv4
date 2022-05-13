package com.igas.express.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.igas.express.model.entitiy.outCome;
import com.igas.express.service.outComeServices;
import com.igas.express.util.ResponseObject;

@RestController
@RequestMapping("api/v1/outcome")
@CrossOrigin(origins = "*")
public class outComeController {
	@Autowired
	private outComeServices outcomeService;
	
	
	//Create income
		 @RequestMapping( method = RequestMethod.POST  )
			public ResponseObject createGasType (@RequestBody outCome outcome ){
				return outcomeService.createOutCome(outcome);
				}
			// update inCome 
			@RequestMapping( method = RequestMethod.PUT , value = "/{id}")
			public  ResponseObject updateGasType (@RequestBody outCome outcome , 
					@PathVariable long id  ){
				    return outcomeService.updateOutCome(id, outcome);
				}
			
			//get all outcome
			@RequestMapping(method = RequestMethod.GET )
			     public ResponseObject getAllOutCome(){
					return outcomeService.getAllOutCome();

	                       }
			
			 //get outcomes form start date to end date
			  @RequestMapping( method = RequestMethod.GET ,value = "/all" )
				public ResponseObject getAllOutcomeFlight(  @RequestParam String startDate , @RequestParam String endDate) {
				return  outcomeService.getAllOutComeFlight( startDate , endDate);
				}
			  
			  //get out comes by id
			  @RequestMapping(method = RequestMethod.GET , value = "/{id}" )
			     public ResponseObject getOutcomeById(@PathVariable long id){
					return outcomeService.getOutComeId(id);

	                       }


}
