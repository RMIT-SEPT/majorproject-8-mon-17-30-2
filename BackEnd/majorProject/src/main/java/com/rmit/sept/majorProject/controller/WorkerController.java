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

import com.rmit.sept.majorProject.model.Person;
import com.rmit.sept.majorProject.model.Worker;
import com.rmit.sept.majorProject.service.WorkerService;

@RestController
@RequestMapping("api/worker")
public class WorkerController implements PersonController{
	
	@Autowired
	private WorkerService workerService;

	
	//REGISTRATION API
    @PostMapping("")
    public ResponseEntity<?> createNewCustomer(@Valid @RequestBody Worker worker, BindingResult result){
        if(result.hasErrors()){
            return new ResponseEntity<Worker>(worker, HttpStatus.BAD_REQUEST);
        }
        workerService.saveOrUpdate(worker);
        return new ResponseEntity<Worker>(worker, HttpStatus.CREATED);
    }
	
    @GetMapping("")
	public List<Worker> getAllPeople() {
		return workerService.getAllPeople();
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
		// TODO Auto-generated method stub
		return null;
	}

}
