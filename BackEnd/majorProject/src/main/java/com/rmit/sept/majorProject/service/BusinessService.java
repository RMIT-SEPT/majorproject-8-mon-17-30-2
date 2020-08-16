package com.rmit.sept.majorProject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rmit.sept.majorProject.model.Business;
import com.rmit.sept.majorProject.model.Person;
import com.rmit.sept.majorProject.repository.BusinessRepository;


@Service
public class BusinessService{

	@Autowired
	private BusinessRepository repository;	
	
	public List<Business> getAllBusinesses()
	{
		return null;
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
	public Boolean updateBusiness(Long id, Person person)
	{
		return repository.updateBusiness(id, person);
	}
}
