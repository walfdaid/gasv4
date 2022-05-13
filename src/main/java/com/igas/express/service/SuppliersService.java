package com.igas.express.service;


import org.springframework.stereotype.Service;

import com.igas.express.model.entitiy.Supplier;
import com.igas.express.util.ResponseObject;



@Service
public interface SuppliersService {
	/*
	 * *Task : find  all suppliers
	 * @param : ---
	 * @return : collection of supplier as List<supplier> else empty list and response massages , code and status
	 */
    public ResponseObject getAllSupplier(); 
    
    
    public ResponseObject updateSupplierStatus(long id,boolean status);
    /*
	 * *Task : find supplier with specified id 
	 * @param : id as long 
	 * @return : suppliers as Suppliers object else null and response massages , code and status
	 */
	public ResponseObject getSupplierById(long id);
	/*
	 * *Task : create new Supplier  
	 * @param : supplier as Supplier object
	 * @return : created supplier id  as long   and response massages , code and status
	 */
	public ResponseObject createSupplier(Supplier supplier );
	/*
	 * *Task : update supplier info  
	 * @param : supplier as Supplier object
	 * @return : updated supplier id  as long and response massages , code and status
	 */
	public ResponseObject updateSupplier(long id ,Supplier supplier );
	/*
	 * *Task : delete with specified id 
	 * @param : id as long 
	 * @return : deleted supplier id  as long and response massages , code and status
	 */
	public ResponseObject deleteSupplier(long id);
	/*
	 * *Task : do all supplier login processes 	    
	 * @param : user name  as String , password as String 
	 * @return : response massages , code and status
	 */
	public ResponseObject loginSupplier(String userName , String password);
	/*
	 * *Task : change supplier password	    
	 * @param : id as long  , password as String 
	 * @return : response massages , code and status
	 */
	public ResponseObject changePassword(String password, long id);
	/*
	 * *Task : update  supplier  location 
	 * @param : longittude as double , latitude as double 
	 * @return : supplier id else null and response massages , code and status
	 */
	public ResponseObject updateSupplierLocation(double lattiude, double longittude , long id );
	public ResponseObject getOnlineSupplier();
	public ResponseObject updateSupplierOnline(long id, boolean online);
	public ResponseObject logOutSuppliers(long id);
	public ResponseObject offlineAllSuppliers();

}
