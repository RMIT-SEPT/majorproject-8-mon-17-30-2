package com.rmit.sept.majorProject.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rmit.sept.majorProject.model.Admin;
import com.rmit.sept.majorProject.model.Person;
import com.rmit.sept.majorProject.repository.AdminRepository;

@Service
public class AdminService implements PersonService {

	@Autowired
	private AdminRepository repository;

	public void saveOrUpdate(Admin admin) {
		repository.save(admin);
	}
	
	public List<Admin> getAllPeople() {
		return (List<Admin>) repository.findAll();
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
		return null;
	}

}
