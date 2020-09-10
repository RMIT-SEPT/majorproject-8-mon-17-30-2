package com.rmit.sept.majorProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.rmit.sept.majorProject.model.Business;
import com.rmit.sept.majorProject.service.BusinessService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class BusinessController {
	
	@Autowired
	private BusinessService businessService;
	
	@GetMapping("api/business")
	public Iterable<Business> getAllBusinesses(){
		return businessService.getAllBusinesses();
	}

}

