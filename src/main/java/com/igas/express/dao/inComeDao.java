package com.igas.express.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.igas.express.model.entitiy.InCome;
@Repository
@Transactional
public interface inComeDao extends CrudRepository<InCome, Long>{
	
	
	public InCome findByinComeId(long id);
	
	public List<InCome> findAll();

}
