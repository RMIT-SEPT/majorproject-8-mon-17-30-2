package com.rmit.sept.majorProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rmit.sept.majorProject.model.Business;
import com.rmit.sept.majorProject.repository.BusinessRepository;

@Service
public class BusinessService{

	@Autowired
	private BusinessRepository repository;	
	
	public Iterable<Business> getAllBusinesses(){
		return repository.findAll();
	}

}
