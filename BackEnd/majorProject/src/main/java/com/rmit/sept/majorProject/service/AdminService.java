package com.rmit.sept.majorProject.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rmit.sept.majorProject.model.Person;
import com.rmit.sept.majorProject.repository.AdminRepository;

@Service
public class AdminService implements PersonService {

	@Autowired
	private AdminRepository repository;

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
		return repository.updatePerson(id, person);
	}

}
