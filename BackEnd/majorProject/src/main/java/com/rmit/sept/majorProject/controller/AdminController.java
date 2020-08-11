package com.rmit.sept.majorProject.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rmit.sept.majorProject.model.Admin;
import com.rmit.sept.majorProject.model.Person;
import com.rmit.sept.majorProject.service.AdminService;

@RestController
@RequestMapping("api/admin")
public class AdminController implements PersonController{
	
	@Autowired
	private AdminService adminService;
	
	//REGISTRATION API
    @PostMapping("")
    public ResponseEntity<?> createNewAdmin(@Valid @RequestBody Admin admin, BindingResult result){
        if(result.hasErrors()){
            return new ResponseEntity<String>("Invalid Admin object", HttpStatus.BAD_REQUEST);
            //For debugging purposes
            //return new ResponseEntity<Admin>(admin, HttpStatus.BAD_REQUEST);            
        }
		adminService.saveOrUpdate(admin);
        return new ResponseEntity<Admin>(admin, HttpStatus.CREATED);
    }
	
    @GetMapping("")
	public List<Admin> getAllPeople()
	{
		return adminService.getAllPeople();
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
