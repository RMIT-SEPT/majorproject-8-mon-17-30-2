package com.rmit.sept.majorProject.service;

import com.rmit.sept.majorProject.dto.BusinessSummary;
import com.rmit.sept.majorProject.dto.CustomerSummary;
import com.rmit.sept.majorProject.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rmit.sept.majorProject.model.Business;
import com.rmit.sept.majorProject.repository.BusinessRepository;

import java.util.ArrayList;

@Service
public class BusinessService{

	@Autowired
	private BusinessRepository repository;	
	
	public Iterable<BusinessSummary> getAllBusinesses(){
		ArrayList<BusinessSummary> businessDtos = new ArrayList<BusinessSummary>();
		Iterable<Business> businesses = repository.findAll();
		for(Business business : businesses){
			businessDtos.add(new BusinessSummary(business));
		}


		return businessDtos;
	}

}
