package com.igas.express.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.igas.express.model.entitiy.SupplierTraceable;



@Repository
public interface SupplierTraceableDao extends CrudRepository<SupplierTraceable, Long> {
	
	

}
