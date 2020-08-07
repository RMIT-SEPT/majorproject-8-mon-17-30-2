package com.rmit.sept.majorProject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.rmit.sept.majorProject.model.Business;
import com.rmit.sept.majorProject.model.Person;
import com.rmit.sept.majorProject.repository.BusinessRepository;


@Service
public class BusinessService {
	
	private final BusinessRepository businessRepository;
	
	BusinessService(@Qualifier("businessRepo") BusinessRepository businessRepository)
	{
		this.businessRepository = businessRepository;
	}
	
	
	public List<Business> getAllBusinesses()
	{
		return null;
	}
	public Business getBusiness(Long ID)
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
	public Boolean deleteBusiness(Long ID)
	{
		return false;
	}
	public Boolean updateBusiness(Long ID, Person person)
	{
		return false;
	}
}
