package com.rmit.sept.majorProject.repository;

import javax.transaction.Transactional;
import com.rmit.sept.majorProject.model.Customer;

@Transactional
public interface CustomerRepository extends PersonRepository<Customer>{
	
	/* Springboot literally reads the names of these abstract methods and creates an SQL query from them.
	Ensure they're named according to the pattern "findByX" where X is the exact name of an attribute. */
	
	// public Customer findByUsername(String username);
	
	public Customer findByEmail(String email);

	public Iterable<Customer> findByAddress(String address);

	public Iterable<Customer> findByPhoneNumber(String phoneNumber);

}