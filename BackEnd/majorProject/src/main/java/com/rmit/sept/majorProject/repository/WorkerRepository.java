package com.rmit.sept.majorProject.repository;

import javax.transaction.Transactional;

import com.rmit.sept.majorProject.model.Service;
import com.rmit.sept.majorProject.model.Worker;

@Transactional
public interface WorkerRepository extends PersonRepository<Worker>{

	/* Springboot literally reads the names of these abstract methods and creates an SQL query from them.
	Ensure they're named according to the pattern "findByX" where X is the exact name of an attribute. */
	
	// public Worker findByUsername(String username);
	
	public Worker findByEmail(String email);

	public Worker findByAddress(String address);

	public Worker findByPhoneNumber(String phoneNumber);

	public Iterable<Service> findServicesById(Long workerId);

}
