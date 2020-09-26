package com.rmit.sept.majorProject.service;

import com.rmit.sept.majorProject.dto.ServiceSummary;
import com.rmit.sept.majorProject.dto.BusinessSummary;
import org.springframework.beans.factory.annotation.Autowired;
import com.rmit.sept.majorProject.model.Business;
import com.rmit.sept.majorProject.model.Service;
import com.rmit.sept.majorProject.repository.BusinessRepository;
import java.util.ArrayList;

@org.springframework.stereotype.Service
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

	public Business findById(Long businessId){
		return repository.findById(businessId).get();
	}

	public Iterable<ServiceSummary> getServicesByBusinessId(Long businessId) {
		ArrayList<ServiceSummary> services = new ArrayList<ServiceSummary>();
		for(Service service : repository.findById(businessId).get().getServices()){
			services.add(new ServiceSummary(service));
		}
		return services;
	}

}
