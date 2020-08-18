package com.rmit.sept.majorProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.rmit.sept.majorProject.model.Customer;
import com.rmit.sept.majorProject.model.Person;
import com.rmit.sept.majorProject.service.CustomerService;

@RestController
public class CustomerController implements PersonController{

    @Autowired
    private CustomerService customerService;
	
    @GetMapping("/api/customer")
	public Iterable<Customer> getAllPeople() {
		return customerService.findAll();
	}

	@Override
	public Boolean addPerson(Person person) {
		return false;
	}

	@Override
	public Person getPerson(Long ID) {
		// TODO Auto-generated method stub
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
