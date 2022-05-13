package com.igas.express.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.igas.express.service.impl.DshboardServicesImpl;
import com.igas.express.util.ResponseObject;

@RestController
@RequestMapping("api/v1/dashboard")
@CrossOrigin(origins = "*")
public class DashBoardController {
	
	
	@Autowired
	private  DshboardServicesImpl dashboardService ;
	
	@RequestMapping(method = RequestMethod.GET )
	 public ResponseObject getAll()
	    { 
		return dashboardService.getAll();
		}
	
	@RequestMapping(method = RequestMethod.GET, value="/today")
	 public ResponseObject getToday()
	    { 
		return dashboardService.getToday();
		}
	
	@RequestMapping(method = RequestMethod.GET, value="/between")
	 public ResponseObject getBetween(@RequestParam String startDate, @RequestParam String endDate)
	    { 
		return dashboardService.getBetween(startDate, endDate);
		}
	
	@RequestMapping(method = RequestMethod.GET, value="/sales")
	 public ResponseObject getSales(){
		return dashboardService.getSales();

	}
	@RequestMapping(method = RequestMethod.GET, value="/salesflight")
	 public ResponseObject getSales(@RequestParam String startDate , @RequestParam String endDate ){
		return dashboardService.getSalesFlight(startDate , endDate);

	}
 

	

}
