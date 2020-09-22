package com.rmit.sept.majorProject.controller;

import com.rmit.sept.majorProject.dto.BusinessSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.rmit.sept.majorProject.service.BusinessService;

@CrossOrigin(origins = "http://agmemonday2.com.s3-website-us-east-1.amazonaws.com")
@RestController
public class BusinessController {
	
	@Autowired
	private BusinessService businessService;
	
	@GetMapping("/api/business")
	public Iterable<BusinessSummary> getAllBusinesses(){
		return businessService.getAllBusinesses();
	}

	@GetMapping("/api/business/{businessId}")
	public ResponseEntity<?> getBusiness(@PathVariable Long businessId){
		BusinessSummary business = new BusinessSummary(businessService.findById(businessId));
		return new ResponseEntity<>(business, business != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
	}

}