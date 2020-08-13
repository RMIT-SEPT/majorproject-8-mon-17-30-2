package com.rmit.sept.majorProject.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import com.rmit.sept.majorProject.model.Customer;
import com.rmit.sept.majorProject.model.Person.Role;
import com.rmit.sept.majorProject.repository.CustomerRepository;

@Service
public class CustomerService implements PersonService<Customer>{

	@Autowired
	private CustomerRepository repository;

	//--------CUSTOMER-SPECIFIC FUNCTIONS----------

	public Customer registerNewCustomer(final Customer customer) throws DuplicateKeyException {
		Customer test = repository.findByEmail(customer.getEmail());
        if(test != null) {
            throw new DuplicateKeyException("An account already exists with email address: " + customer.getEmail());
		}
		else if(repository.findByUsername(customer.getUsername()) != null) {
			throw new DuplicateKeyException(("An account already exists with username: " + customer.getUsername()));
		}
        Customer newCustomer = new Customer(customer);
        return repository.save(newCustomer);
	}

	//---------GENERIC PERSON FUNCTIONS------------	

	@Override
	public long count() {
		return repository.count();
	}

	@Override
	public void delete(Customer person) {
		repository.delete(person);
	}

	@Override
	public void deleteAll() {
		repository.deleteAll();
	}

	@Override
	public void deleteAll(Iterable<Customer> persons) {
		repository.deleteAll(persons);
	}

	@Override
	public void deleteById(long id) {
		repository.deleteById(id);
	}

	@Override
	public boolean existsById(long id) {
		return repository.existsById(id);
	}

	@Override
	public Iterable<Customer> findAll() {
		return repository.findAll();
	}

	@Override
	public Iterable<Customer> findAllById(Iterable<Long> ids) {
		return repository.findAllById(ids);
	}

	@Override
	public Optional<Customer> findById(long id) {
		return repository.findById(id);
	}

	@Override
	public Customer save(Customer person) {
		return repository.save(person);
	}

	@Override
	public Iterable<Customer> saveAll(Iterable<Customer> persons) {
		return repository.saveAll(persons);
	}

	@Override
	public Iterable<Customer> findByName(String name) {
		return repository.findByName(name);
	}

	@Override
	public Customer findByUsername(String username) {
		return repository.findByUsername(username);
	}

	@Override
	public Iterable<Customer> findByRole(Role role) {
		return repository.findByRole(role);
	}


}
