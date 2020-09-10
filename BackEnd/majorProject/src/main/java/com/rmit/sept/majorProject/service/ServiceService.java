package com.rmit.sept.majorProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.rmit.sept.majorProject.dto.ServiceSummary;
import com.rmit.sept.majorProject.model.Worker;
import com.rmit.sept.majorProject.repository.ServiceRepository;
import com.rmit.sept.majorProject.repository.WorkerRepository;

@Service
public class ServiceService{

	@Autowired
    private ServiceRepository repository;	
    @Autowired
    private WorkerRepository workerRepository;
	
	public Iterable<com.rmit.sept.majorProject.model.Service> findAll(){
		return repository.findAll();
	}

    public Iterable<com.rmit.sept.majorProject.model.Service> findByBusinessId(Long businessId){
        return repository.findByBusinessId(businessId);
    }

    public Iterable<com.rmit.sept.majorProject.model.Service> findByWorkerId(Long workerId){
        Iterable<com.rmit.sept.majorProject.model.Service> matchingServices;
        Worker worker = workerRepository.findById(workerId).get();
        matchingServices = worker != null ? worker.getServices() : new ArrayList<com.rmit.sept.majorProject.model.Service>();
        return matchingServices;
    }
    
    public Iterable<ServiceSummary> findByWorkerIdDTO(Long workerId){
        ArrayList<ServiceSummary> serviceDtos = new ArrayList<ServiceSummary>();
        for(com.rmit.sept.majorProject.model.Service service : findByWorkerId(workerId)){
            serviceDtos.add(new ServiceSummary(service));
        }
        return serviceDtos;
    }

}
