package com.rmit.sept.majorProject.repository;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.rmit.sept.majorProject.model.Person;

@Repository("adminRepo")
public class AdminRepository implements PersonRepository{
	
	private List<Person> adminList;
	
	AdminRepository()
	{
		this.adminList = new LinkedList<Person>();
	}

	@Override
	public List<Person> getAllPeople() {
		return this.adminList;
	}

	@Override
	public Person getPerson(Long ID) {
		for(Person person:this.adminList)
		{
			if(person.getID() == ID)
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
