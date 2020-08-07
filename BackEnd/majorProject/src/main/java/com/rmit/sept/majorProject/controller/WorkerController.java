package com.rmit.sept.majorProject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rmit.sept.majorProject.model.Person;
import com.rmit.sept.majorProject.service.PersonService;

@RestController
@RequestMapping("api/worker")
public class WorkerController implements PersonController{
	private final PersonService workerService;

	@Autowired
	public WorkerController(PersonService workerService)
	{
		this.workerService = workerService;
	}
	
	@Override
	public List<Person> getAllPeople() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean addPerson(Person person) {
		// TODO Auto-generated method stub
		return null;
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
