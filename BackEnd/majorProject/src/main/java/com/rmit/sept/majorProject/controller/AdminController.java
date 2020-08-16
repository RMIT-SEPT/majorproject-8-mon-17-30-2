package com.rmit.sept.majorProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.rmit.sept.majorProject.model.Admin;
import com.rmit.sept.majorProject.model.Person;
import com.rmit.sept.majorProject.service.AdminService;

@RestController
public class AdminController implements PersonController{
	
	@Autowired
	private AdminService adminService;
	
    @GetMapping("/api/admin")
	public Iterable<Admin> getAllPeople()
	{
		return adminService.findAll();
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
