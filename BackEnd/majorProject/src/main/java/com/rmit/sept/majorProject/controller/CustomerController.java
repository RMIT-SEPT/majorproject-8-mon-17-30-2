package com.rmit.sept.majorProject.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.rmit.sept.majorProject.dto.CustomerSummary;
import com.rmit.sept.majorProject.model.Customer;
import com.rmit.sept.majorProject.service.CustomerService;
import com.rmit.sept.majorProject.utility.Util;

@CrossOrigin(origins = Util.API_HOST)
@RestController
public class CustomerController {

    @Autowired
	private CustomerService customerService;

	@GetMapping("/api/customer")
	public Iterable<CustomerSummary> getAllCustomerDtos() {
		return customerService.findAllDTO();
	}

	@GetMapping("/api/customer/{customerId}")
	public ResponseEntity<?> getCustomer(@PathVariable Long customerId){
		if(customerId == null || customerId <= 0){
			return new ResponseEntity<String>("Invalid customer ID", HttpStatus.BAD_REQUEST);
		}
		CustomerSummary summary = customerService.findByIdDTO(customerId);
		return new ResponseEntity<>(summary, summary != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/api/customer/{id}")
    public ResponseEntity<?> updateCustomer(@Valid @RequestBody Customer newCustomer, @PathVariable Long id){
		if(id == null || id <= 0){
			return new ResponseEntity<String>("Invalid customer ID", HttpStatus.BAD_REQUEST);
		}
		CustomerSummary exist = customerService.editCustomer(id, newCustomer);
		return new ResponseEntity<>(exist, exist != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

}
