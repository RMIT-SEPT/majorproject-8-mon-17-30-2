package com.rmit.sept.majorProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
