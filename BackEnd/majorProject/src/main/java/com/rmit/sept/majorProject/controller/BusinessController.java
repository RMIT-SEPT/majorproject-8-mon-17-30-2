package com.rmit.sept.majorProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.rmit.sept.majorProject.model.Business;
import com.rmit.sept.majorProject.model.Person;
import com.rmit.sept.majorProject.service.BusinessService;

@RestController
public class BusinessController {
	
	@Autowired
	private BusinessService businessService;
	
	@Autowired
	public  BusinessController(BusinessService businessService)
	{
		this.businessService = businessService;
	}
	
	@GetMapping("api/business")
	public Iterable<Business> getAllBusinesses()
	{
		return businessService.getAllBusinesses();
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

