package com.rmit.sept.majorProject.controller;

import com.rmit.sept.majorProject.dto.AdminSummary;
import com.rmit.sept.majorProject.dto.CustomerSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.rmit.sept.majorProject.model.Admin;
import com.rmit.sept.majorProject.model.Person;
import com.rmit.sept.majorProject.service.AdminService;
@CrossOrigin(origins = "http://localhost:3000")
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

	@GetMapping("/api/admin/{adminUsername}")
	public ResponseEntity<?> getCustomer(@PathVariable String adminUsername){
		AdminSummary admin = adminService.getAdmin(adminUsername);
		return new ResponseEntity<>(admin, admin != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
	}


}
