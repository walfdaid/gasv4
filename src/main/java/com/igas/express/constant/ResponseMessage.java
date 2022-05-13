package com.igas.express.constant;

public class ResponseMessage {
	// fail message for all 
	public final static String FAILED_GETTING_MESSAGE = "not found"; 
	public final static String FAILED_PATCHING_MESSAGE = "Invalid id ";
	public final static String FAILED_DELETTING_MESSAGE = "Invalid id or already deleted";
	public final static String FAILED_LOGIN_MESSAGE = "Invalid phone number or password ";
    public final static String FAILED_STRING_MESSAGE = "wrong date format";
    public final static String FAILED_CREATING_MESSAGE = "duplicate params";
    public final static String FAILED_UPDATING_MESSAGE = "duplicate params";
    public final static String FAILED_NO_SUPPlIER = "no supplier found";
	// success messages for all 
	public final static String SUCCESS_GETTING_MESSAGE = "Found";
	public final static String SUCCESS_CREATING_MESSAGE = "Created";
	public final static String SUCCESS_UPDATING_MESSAGE = "Updated";
	public final static String SUCCESS_PATCHING_MESSAGE = "Changed";
	public final static String SUCCESS_DELETTING_MESSAGE = "Deleted";
	public final static String SUCCESS_LOGIN_MESSAGE = "logged on successfully";	
	
	


	

}
