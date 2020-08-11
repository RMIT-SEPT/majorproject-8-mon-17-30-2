package com.rmit.sept.majorProject.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rmit.sept.majorProject.model.Customer;
import com.rmit.sept.majorProject.model.Person;
import com.rmit.sept.majorProject.model.Worker;
import com.rmit.sept.majorProject.repository.WorkerRepository;

@Service
public class WorkerService implements PersonService {

	@Autowired
	private WorkerRepository repository;
	
	
	public void saveOrUpdate(Worker worker) {
		repository.save(worker);		
	}
	
	public List<Worker> getAllPeople() {
		return (List<Worker>) repository.findAll();
	}

	@Override
	public Boolean addPerson(Person person) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Person getPerson(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean deletePerson(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean updatePerson(Long id, Person person) {
		return null;
	}

	

}
