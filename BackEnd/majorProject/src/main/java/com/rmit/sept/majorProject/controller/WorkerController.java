package com.rmit.sept.majorProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import com.rmit.sept.majorProject.dto.WorkerSummary;
import com.rmit.sept.majorProject.model.Worker;
import com.rmit.sept.majorProject.service.WorkerService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class WorkerController{
	
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

	@GetMapping("/api/worker/{workerId}")
	public ResponseEntity<?> getCustomer(@PathVariable Long workerId){
		WorkerSummary worker = workerService.findByIdDTO(workerId);
		return new ResponseEntity<>(worker, worker != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
	}

}
