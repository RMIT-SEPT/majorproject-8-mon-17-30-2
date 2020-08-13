package com.rmit.sept.majorProject.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rmit.sept.majorProject.model.Worker;
import com.rmit.sept.majorProject.model.Person.Role;
import com.rmit.sept.majorProject.repository.WorkerRepository;

@Service
public class WorkerService implements PersonService<Worker>{

	@Autowired
	private WorkerRepository repository;

	//---------WORKER-SPECIFIC FUNCTIONS-----------
	
	//---------GENERIC PERSON FUNCTIONS------------
	
	@Override
	public long count() {
		return repository.count();
	}

	@Override
	public void delete(Worker person) {
		repository.delete(person);
	}

	@Override
	public void deleteAll() {
		repository.deleteAll();
	}

	@Override
	public void deleteAll(Iterable<Worker> persons) {
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
	public Iterable<Worker> findAll() {
		return repository.findAll();
	}

	@Override
	public Iterable<Worker> findAllById(Iterable<Long> ids) {
		return repository.findAllById(ids);
	}

	@Override
	public Optional<Worker> findById(long id) {
		return repository.findById(id);
	}

	@Override
	public Worker save(Worker person) {
		return repository.save(person);
	}

	@Override
	public Iterable<Worker> saveAll(Iterable<Worker> persons) {
		return repository.saveAll(persons);
	}

	@Override
	public Iterable<Worker> findByName(String name) {
		return repository.findByName(name);
	}

	@Override
	public Worker findByUsername(String username) {
		return repository.findByUsername(username);
	}

	@Override
	public Iterable<Worker> findByRole(Role role) {
		return repository.findByRole(role);
	}	

}
