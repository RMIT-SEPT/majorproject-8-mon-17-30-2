package com.rmit.sept.majorProject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rmit.sept.majorProject.model.Person;
import com.rmit.sept.majorProject.service.PersonService;

@RestController
@RequestMapping("api/admin")
public class AdminController implements PersonController{
	private final PersonService adminService;
	
	@Autowired
	public AdminController(PersonService adminService)
	{
		this.adminService = adminService;
	}
	
	public List<Person> getAllPeople()
	{
		return null;
	}
	
	public Boolean addPerson(Person person)
	{
		return false;
	}
	
	public Person getPerson(Long ID)
	{
		return null;
	}
	
	public Boolean deletePerson(Long ID)
	{
		return false;
	}
	
	public Boolean updatePerson(Long ID, Person person)
	{
		return false;
	}
}
