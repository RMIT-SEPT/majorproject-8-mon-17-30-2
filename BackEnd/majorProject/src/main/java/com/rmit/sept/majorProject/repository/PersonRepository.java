package com.rmit.sept.majorProject.repository;

import java.util.List;

import com.rmit.sept.majorProject.model.Person;

public interface PersonRepository {
	
	public List<Person> getAllPeople();
	
	public Person getPerson(Long ID);
	
	public Boolean addPerson(Person person);
	
	public Boolean deletePerson(Long ID);
	
	public Boolean updatePerson(Long ID, Person person);
}
