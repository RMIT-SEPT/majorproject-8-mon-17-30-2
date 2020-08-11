package com.rmit.sept.majorProject.repository;
import com.rmit.sept.majorProject.model.Customer;
import com.rmit.sept.majorProject.model.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("custRepo")
public interface CustomerRepository extends CrudRepository<Customer, Long>, PersonRepository{
	
	@Override
	Iterable<Customer> findAllById(Iterable<Long> id);
	
	@Override
	Iterable<Customer> findAll();
	
	Customer findCustomerByUsername(String username);

}