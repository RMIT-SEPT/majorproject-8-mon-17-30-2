package com.rmit.sept.majorProject.repository;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.rmit.sept.majorProject.model.Business;
import com.rmit.sept.majorProject.model.Person;

@Repository("businessRepo")
public class BusinessRepository {
	private List<Business> businessList;
	
	BusinessRepository()
	{
		this.businessList = new LinkedList<Business>();
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
