package com.rmit.sept.majorProject.controller;

import com.rmit.sept.majorProject.model.Person;

public interface PersonController {

//	public abstract List<Person> getAllPeople();
	
	public abstract Boolean addPerson(Person person);
	
	public abstract Person getPerson(Long ID);
	
	public abstract Boolean deletePerson(Long ID);
	
	public abstract Boolean updatePerson(Long ID, Person person);
}
