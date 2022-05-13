package com.igas.express.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.igas.express.model.entitiy.outCome;
@Repository
@Transactional
public interface outComeDao extends CrudRepository<outCome, Long> {

	
	public outCome findByoutComeId(long id);
	public List<outCome> findAll();


}
