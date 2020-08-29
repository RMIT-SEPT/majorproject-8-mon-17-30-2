package com.rmit.sept.majorProject.controller;

import com.rmit.sept.majorProject.dto.AdminSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import com.rmit.sept.majorProject.dto.WorkerSummary;
import com.rmit.sept.majorProject.model.Person;
import com.rmit.sept.majorProject.model.Worker;
import com.rmit.sept.majorProject.service.WorkerService;

@RestController

public class WorkerController implements PersonController{
	
	@Autowired
	private WorkerService workerService;


	public Iterable<Worker> getAllWorkers() {
		return workerService.findAll();
	}
	
    @GetMapping("/api/worker")
	public Iterable<WorkerSummary> getAllWorkerDtos() {
		ArrayList<WorkerSummary> workerDtos = new ArrayList<WorkerSummary>();
		Iterable<Worker> workers = workerService.findAll();
		for(Worker worker : workers){
			workerDtos.add(new WorkerSummary(worker));
		}
		return workerDtos;
	}

	@GetMapping("/api/worker/{workerUsername}")
	public ResponseEntity<?> getCustomer(@PathVariable String workerUsername){
		WorkerSummary worker = workerService.findByUsernameSummary(workerUsername);
		return new ResponseEntity<>(worker, worker != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
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
