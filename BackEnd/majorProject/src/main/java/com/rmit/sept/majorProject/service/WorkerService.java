package com.rmit.sept.majorProject.service;

import java.util.ArrayList;
import java.util.Optional;
import com.rmit.sept.majorProject.dto.WorkerSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.rmit.sept.majorProject.model.Worker;
import com.rmit.sept.majorProject.model.Person.Role;
import com.rmit.sept.majorProject.repository.WorkerRepository;

@Service
public class WorkerService implements PersonService<Worker>{

	@Autowired
	private WorkerRepository repository;
	@Autowired
	private DuplicateCheckService duplicateCheck;

	//---------WORKER-SPECIFIC FUNCTIONS-----------

	public Worker registerNewWorker(final Worker worker) throws DuplicateKeyException {
		String email = worker.getEmail();
		String username = worker.getUsername();
        if(duplicateCheck.emailExists(email)) {
            throw new DuplicateKeyException("An account already exists with email address: " + email);
		}
		if(duplicateCheck.usernameExists(username)) {
            throw new DuplicateKeyException("An account already exists with username: " + username);
		}
		Worker newWorker = new Worker(worker);
		return repository.save(newWorker);
	}

	//---------------DTO FUNCTIONS--------------

	public Iterable<WorkerSummary> getAllWorkersDTO(){
		ArrayList<WorkerSummary> workerDtos = new ArrayList<WorkerSummary>();
		Iterable<Worker> workers = repository.findAll();
		for(Worker worker : workers){
			workerDtos.add(new WorkerSummary(worker));
		}
		return workerDtos;
	}

	public WorkerSummary findByUsernameDTO(String username) {
		Worker worker = repository.findByUsername(username);
		WorkerSummary summary = null;
		if(worker == null){
			throw new UsernameNotFoundException("Worker not found in the database");
		} else {
			summary = new WorkerSummary(worker);
		}
		return summary;
	}

	public WorkerSummary findByIdDTO(Long id){
		WorkerSummary summary = null;
		Optional<Worker> workerOptional = repository.findById(id);
		Worker workerFound = workerOptional.get();
		if (workerFound != null){
			summary = new WorkerSummary(workerFound);
		}
		return summary;
	}

	public Worker editWorker(Long workerId, Worker newWorker){
		// WorkerSummary selectedWorker = null;
		Optional<Worker> workerOptional = repository.findById(workerId);
		Worker workerFound = workerOptional.get();
		if (workerFound != null){
			if (newWorker.getBusiness() != null) {
				workerFound.setBusiness(newWorker.getBusiness());
			}
			if (newWorker.getPassword() != null) {
				workerFound.setPassword(newWorker.getPassword());
			}
			if (newWorker.getServices() != null) {
				workerFound.setServices(newWorker.getServices());
			}
			if (newWorker.getAddress() != null) {
				workerFound.setAddress(newWorker.getAddress());
			}
			if (newWorker.getEmail() != null) {
				workerFound.setEmail(newWorker.getEmail());
			}
			if (newWorker.getUsername() != null) {
				workerFound.setUsername(newWorker.getUsername());
			}
			if (newWorker.getPhoneNumber() != null) {
				workerFound.setPhoneNumber((newWorker.getPhoneNumber()));
			}
		}
		repository.save(workerFound);
		return workerFound;
	}
	
	//---------GENERIC PERSON FUNCTIONS------------
	
	@Override
	public long count() {
		return repository.count();
	}

	@Override
	public void delete(Worker person) {
		repository.delete(person);
	}

	@Override
	public void deleteAll() {
		repository.deleteAll();
	}

	@Override
	public void deleteAll(Iterable<Worker> persons) {
		repository.deleteAll(persons);
	}

	@Override
	public void deleteById(long id) {
		repository.deleteById(id);
	}

	@Override
	public boolean existsById(long id) {
		return repository.existsById(id);
	}

	@Override
	public Iterable<Worker> findAll() {
		return repository.findAll();
	}

	@Override
	public Iterable<Worker> findAllById(Iterable<Long> ids) {
		return repository.findAllById(ids);
	}

	@Override
	public Optional<Worker> findById(long id) {
		return repository.findById(id);
	}

	@Override
	public Worker save(Worker person) {
		return repository.save(person);
	}

	@Override
	public Iterable<Worker> saveAll(Iterable<Worker> persons) {
		return repository.saveAll(persons);
	}

	@Override
	public Iterable<Worker> findByName(String name) {
		return repository.findByName(name);
	}

	@Override
	public Worker findByUsername(String username){
		return repository.findByUsername(username);
	}

	@Override
	public Iterable<Worker> findByRole(Role role) {
		return repository.findByRole(role);
	}

}
