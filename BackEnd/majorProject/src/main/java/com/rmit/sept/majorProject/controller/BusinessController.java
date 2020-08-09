package com.rmit.sept.majorProject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rmit.sept.majorProject.model.Business;
import com.rmit.sept.majorProject.model.Person;
import com.rmit.sept.majorProject.service.BusinessService;

@RestController
@RequestMapping("api/business")
public class BusinessController {
	private final BusinessService businessService;
	
	@Autowired
	public  BusinessController(BusinessService businessService)
	{
		this.businessService = businessService;
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

