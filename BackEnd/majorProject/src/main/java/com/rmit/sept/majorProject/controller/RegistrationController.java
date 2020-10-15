package com.rmit.sept.majorProject.controller;

import javax.validation.Valid;

import com.rmit.sept.majorProject.dto.AdminSummary;
import com.rmit.sept.majorProject.dto.CustomerSummary;
import com.rmit.sept.majorProject.dto.WorkerBlueprint;
import com.rmit.sept.majorProject.dto.WorkerSummary;
import com.rmit.sept.majorProject.model.Admin;
import com.rmit.sept.majorProject.model.Customer;
import com.rmit.sept.majorProject.model.Worker;
import com.rmit.sept.majorProject.service.AdminService;
import com.rmit.sept.majorProject.service.CustomerService;
import com.rmit.sept.majorProject.service.WorkerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.*;
import com.rmit.sept.majorProject.utility.Util;

@CrossOrigin(origins = Util.API_HOST)
@RestController
@RequestMapping("api/")
public class RegistrationController {
    
    @Autowired
    private CustomerService customerService;
    @Autowired
    private WorkerService workerService;
    @Autowired
    private AdminService adminService;

    // CUSTOMER REGISTRATION API

    @PostMapping("customer/register")
	public ResponseEntity<?> registerCustomerAccount(@Valid @RequestBody Customer customer){	    
	    try {
	        customerService.registerNewCustomer(customer);
	    } catch (DuplicateKeyException dkEx) {
	        return new ResponseEntity<String>(dkEx.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<CustomerSummary>(new CustomerSummary(customer), HttpStatus.CREATED);        
    }
    
    // WORKER REGISTRATION API
    @PostMapping("worker/register")
    public ResponseEntity<?> registerWorkerAccount(@Valid @RequestBody WorkerBlueprint worker){
    	Worker savedWorker;
        try {
        	savedWorker = workerService.registerNewWorkerByBlueprint(worker);
        } catch (DuplicateKeyException dkEx) {
            return new ResponseEntity<String>(dkEx.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<WorkerSummary>(new WorkerSummary(savedWorker), HttpStatus.CREATED);        
    }

    // ADMIN REGISTRATION API
    @PostMapping("admin/register")
    public ResponseEntity<?> registerAdminAccount(@Valid @RequestBody Admin admin){	    
    	Admin savedAdmin;
        try {
            savedAdmin = adminService.registerNewAdmin(admin);
        } catch (DuplicateKeyException dkEx) {
            return new ResponseEntity<String>(dkEx.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<AdminSummary>(new AdminSummary(savedAdmin), HttpStatus.CREATED);        
    }
    
}