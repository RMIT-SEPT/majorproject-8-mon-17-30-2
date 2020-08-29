package com.rmit.sept.majorProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.rmit.sept.majorProject.dto.CustomerSummary;
import com.rmit.sept.majorProject.model.Customer;
import com.rmit.sept.majorProject.service.CustomerService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;	
    
	public Iterable<Customer> getAllCustomers() {
		return customerService.findAll();
	}

	@GetMapping("/api/customer")
	public Iterable<CustomerSummary> getAllCustomerDtos() {
		return customerService.findAllDTO();
	}

	@GetMapping("/api/customer/{customerId}")
	public ResponseEntity<?> getCustomer(@PathVariable Long customerId){
		CustomerSummary summary = customerService.findByIdDTO(customerId);
		return new ResponseEntity<>(summary, summary != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
	}

	

}
