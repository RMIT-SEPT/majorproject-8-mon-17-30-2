package com.rmit.sept.majorProject.repository;

import javax.transaction.Transactional;
import com.rmit.sept.majorProject.model.Customer;

@Transactional
public interface CustomerRepository extends PersonRepository<Customer>{
	
	public Customer findByEmail(String email);

	public Iterable<Customer> findByAddress(String address);

	public Iterable<Customer> findByPhoneNumber(String phoneNumber);

}