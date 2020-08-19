package com.rmit.sept.majorProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rmit.sept.majorProject.model.Business;
import com.rmit.sept.majorProject.model.Person;
import com.rmit.sept.majorProject.repository.BusinessRepository;

@Service
public class BusinessService{

	@Autowired
	private BusinessRepository repository;	
	
	public Iterable<Business> getAllBusinesses()
	{
		return repository.findAll();
	}
	public Business getBusiness(Long id)
	{
		return null;
	}
	public Person getOwner(Business business)
	{
		return null;
	}
	public Boolean addBusiness(Business business)
	{
		return false;
	}
	public Boolean deleteBusiness(Long id)
	{
		return false;
	}
}
