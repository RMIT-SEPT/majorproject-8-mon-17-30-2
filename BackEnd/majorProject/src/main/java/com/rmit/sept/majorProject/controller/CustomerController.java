package com.rmit.sept.majorProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

import com.rmit.sept.majorProject.dto.CustomerSummary;
import com.rmit.sept.majorProject.model.Customer;
import com.rmit.sept.majorProject.model.Person;
import com.rmit.sept.majorProject.service.CustomerService;

@RestController
public class CustomerController implements PersonController{

    @Autowired
    private CustomerService customerService;
	
    
	public Iterable<Customer> getAllCustomers() {
		return customerService.findAll();
	}

	@GetMapping("/api/customer")
	public Iterable<CustomerSummary> getAllCustomerDtos() {
		return customerService.getAllCustomersDTO();
	}

	@Override
	public Boolean addPerson(Person person) {
		return false;
	}

	@Override
	public Person getPerson(Long ID) {

		return null;
	}

	@Override
	public Boolean deletePerson(Long ID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean updatePerson(Long ID, Person person) {
		// TODO Auto-generated method stub
		return null;
	}

}
