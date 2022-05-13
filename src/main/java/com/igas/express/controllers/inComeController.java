package com.igas.express.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.igas.express.model.entitiy.InCome;
import com.igas.express.service.inComeServices;
import com.igas.express.util.ResponseObject;

@RestController
@RequestMapping("api/v1/income")
@CrossOrigin(origins = "*")
public class inComeController {
	@Autowired
	 private inComeServices incomeService;
	 
	 //Create income
 @RequestMapping( method = RequestMethod.POST  )
		public ResponseObject createGasType (@RequestBody InCome Income ){
			return incomeService .createInCome(Income);
			}
		// update inCome 
		@RequestMapping( method = RequestMethod.PUT , value = "/{id}")
		public  ResponseObject updateGasType (@RequestBody InCome Income , 
				@PathVariable long id  ){
			    return incomeService.updateInCome(id, Income);
			}
		
		//gat all in come
		  @RequestMapping(method = RequestMethod.GET )
		     public ResponseObject getAllIncome(){
				return incomeService.getAllInCome();

                      }
		  
		  
		  @RequestMapping( method = RequestMethod.GET ,value = "/all" )
			public ResponseObject getAllIncomeFlight( @RequestParam String startDate , @RequestParam String endDate) {
			return  incomeService.getAllInComeFlight( startDate , endDate);
			}
		  
		//in come
		  @RequestMapping(method = RequestMethod.GET , value = "/{id}" )
		     public ResponseObject getIncomById(@PathVariable long id ){
				return incomeService.getIncomeById(id);

                      }

}
