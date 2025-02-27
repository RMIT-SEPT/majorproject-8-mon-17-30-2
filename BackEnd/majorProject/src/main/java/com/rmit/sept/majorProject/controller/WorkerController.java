package com.rmit.sept.majorProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;

import javax.validation.Valid;

import com.rmit.sept.majorProject.dto.WorkerSummary;
import com.rmit.sept.majorProject.model.Worker;
import com.rmit.sept.majorProject.service.WorkerService;
import com.rmit.sept.majorProject.utility.Util;

@CrossOrigin(origins = Util.API_HOST)
@RestController
public class WorkerController{
	
	@Autowired
	private WorkerService workerService;

	public Iterable<Worker> getAllWorkers() {
		return workerService.findAll();
	}	

	@GetMapping("/api/worker/business/{businessId}")
	public Iterable<WorkerSummary> getAllWorkerDtosFromBusiness(@PathVariable Long businessId) {
		if(businessId <= 0 || businessId == null) {
			return null;
		}
		ArrayList<WorkerSummary> workerDtos = new ArrayList<WorkerSummary>();
		workerDtos = workerService.getAllWorkerDtosFromBusiness(businessId);

		return workerDtos;
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
		if(workerId <= 0 || workerId == null) {
			return new ResponseEntity<String>("Invalid worker ID", HttpStatus.BAD_REQUEST);
		}
		WorkerSummary worker = workerService.findByIdDTO(workerId);
		return new ResponseEntity<>(worker, worker != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
	}


	@RequestMapping(method = RequestMethod.PUT, value = "/api/worker/edit/{id}")
	public ResponseEntity<?> updateWorker(@Valid @RequestBody Worker newWorker, @PathVariable Long id) {
		if (id <= 0 || id == null) {
			return new ResponseEntity<String>("Invalid worker ID", HttpStatus.BAD_REQUEST);
		}
		WorkerSummary exist = workerService.editWorker(id ,newWorker);
		return new ResponseEntity<>(exist, exist != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }
	
}
