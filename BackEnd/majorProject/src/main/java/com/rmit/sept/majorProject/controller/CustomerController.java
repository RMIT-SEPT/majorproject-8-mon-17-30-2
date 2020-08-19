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
	
    @GetMapping("/api/customer")
	public Iterable<CustomerSummary> getAllCustomers() {
		ArrayList<CustomerSummary> customerDtos = new ArrayList<CustomerSummary>();
		Iterable<Customer> customers = customerService.findAll();
		for(Customer customer : customers){
			customerDtos.add(new CustomerSummary(customer));
		}
		return customerDtos;
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
