package com.rmit.sept.majorProject.controller;

import javax.validation.Valid;
import com.rmit.sept.majorProject.model.Customer;
import com.rmit.sept.majorProject.service.CustomerService;
// import com.rmit.sept.majorProject.service.WorkerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {
    
    @Autowired
    private CustomerService customerService;

    // @Autowired
    // private WorkerService workerService;


    // CUSTOMER REGISTRATION API
    @PostMapping("/api/customer/register")
	public ResponseEntity<?> registerCustomerAccount(@Valid @RequestBody Customer customer){	    
	    try {
	        customerService.registerNewCustomer(customer);
	    } catch (DuplicateKeyException dkEx) {
	        return new ResponseEntity<String>(dkEx.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Customer>(customer, HttpStatus.CREATED);        
	}
    
}