package com.rmit.sept.majorProject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.rmit.sept.majorProject.model.Person;
import com.rmit.sept.majorProject.repository.PersonRepository;

@Service
public class WorkerService implements PersonService{

	private final PersonRepository workerRepository;
	
	WorkerService(@Qualifier("workerRepo") PersonRepository workerRepository)
	{
		this.workerRepository = workerRepository;
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
