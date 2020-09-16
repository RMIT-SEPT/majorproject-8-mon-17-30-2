package com.rmit.sept.majorProject.service;

import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import com.rmit.sept.majorProject.dto.CustomerSummary;
import com.rmit.sept.majorProject.model.Customer;
import com.rmit.sept.majorProject.model.Person.Role;
import com.rmit.sept.majorProject.repository.CustomerRepository;

@Service
public class CustomerService implements PersonService<Customer>{

	@Autowired
	private CustomerRepository repository;
	@Autowired
	private DuplicateCheckService duplicateCheck;

	//--------CUSTOMER-SPECIFIC FUNCTIONS----------

	public Customer registerNewCustomer(final Customer customer) throws DuplicateKeyException {
		String email = customer.getEmail();
		String username = customer.getUsername();
        if(duplicateCheck.emailExists(email)) {
            throw new DuplicateKeyException("An account already exists with email address: " + email);
		}
		if(duplicateCheck.usernameExists(username)) {
            throw new DuplicateKeyException("An account already exists with username: " + username);
		}
        Customer newCustomer = new Customer(customer);
        return repository.save(newCustomer);
	}

	public CustomerSummary editCustomer(Long customerId, Customer newCustomer){
		Optional<Customer> customerOptional = repository.findById(customerId);
		Customer customerFound = customerOptional.get();
		if (customerFound != null){
			if(newCustomer.getAddress() != null) {
				customerFound.setAddress(newCustomer.getAddress());
			}
			if(newCustomer.getEmail() != null) {
				customerFound.setEmail(newCustomer.getEmail());
			}
			if(newCustomer.getPassword() != null) {
				customerFound.setPassword(newCustomer.getPassword());
			}
			if(newCustomer.getPhoneNumber() != null) {
				customerFound.setPhoneNumber(newCustomer.getPhoneNumber());
			}
			if(newCustomer.getUsername() != null) {
				customerFound.setUsername(newCustomer.getUsername());
			}
		}
		repository.save(customerFound);
		return new CustomerSummary(customerFound);
	}
	

	//---------------DTO FUNCTIONS--------------	

	public Iterable<CustomerSummary> findAllDTO(){
		ArrayList<CustomerSummary> customerDtos = new ArrayList<CustomerSummary>();
		Iterable<Customer> customers = repository.findAll();
		for(Customer customer : customers){
			customerDtos.add(new CustomerSummary(customer));
		}
		return customerDtos;
	}

	public CustomerSummary findByIdDTO(Long id){
		CustomerSummary summary = null;
		Optional<Customer> customerOptional = repository.findById(id);
		Customer customerFound = customerOptional.get();
		if (customerFound != null){
			summary = new CustomerSummary(customerFound);
		}
		return summary;
	}

	public CustomerSummary findByUsernameDTO(String username){
		CustomerSummary summary = null;
		Customer customerFound = repository.findByUsername(username);
		if (customerFound != null){
			summary = new CustomerSummary(customerFound);
		}
		return summary;
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
