package com.rmit.sept.majorProject.repository;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.rmit.sept.majorProject.model.Person;

@Repository("customerRepo")
public class CustomerRepository implements PersonRepository{
	
	private List<Person> customerList;

	CustomerRepository()
	{
		this.customerList = new LinkedList<Person>();
	}
	
	@Override
	public List<Person> getAllPeople() {
		return this.customerList;
	}

	@Override
	public Person getPerson(Long id) {
		for(Person person:this.customerList)
		{
			if(person.getId() == id)
			{
				return person;
			}
		}
		return null;
	}

	@Override
	public Boolean addPerson(Person person) {
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
		// TODO Auto-generated method stub
		return null;
	}

}
