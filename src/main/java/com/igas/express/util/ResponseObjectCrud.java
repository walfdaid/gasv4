package com.igas.express.util;

public class ResponseObjectCrud extends ResponseObject {
	
	private long id;
	
	
	public ResponseObjectCrud(String status, String code, String message , long id ) {
		super(status, code, message);
		setId(id);
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	
}
