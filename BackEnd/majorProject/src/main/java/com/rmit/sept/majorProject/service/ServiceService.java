package com.rmit.sept.majorProject.service;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import com.rmit.sept.majorProject.dto.ServiceSummary;
import com.rmit.sept.majorProject.model.Service;
import com.rmit.sept.majorProject.model.Worker;
import com.rmit.sept.majorProject.repository.ServiceRepository;
import com.rmit.sept.majorProject.repository.WorkerRepository;

@org.springframework.stereotype.Service
public class ServiceService{

	@Autowired
    private ServiceRepository repository;	
    @Autowired
    private WorkerRepository workerRepository;
	
	public Iterable<Service> findAll(){
		return repository.findAll();
    }
    
    public Service findById(Long id){
        return repository.findById(id).get();
    }

    public Iterable<Service> findByBusinessId(Long businessId){
        return repository.findByBusinessId(businessId);
    }

    public Iterable<Service> findByWorkerId(Long workerId){
        Iterable<Service> matchingServices;
        Worker worker = workerRepository.findById(workerId).get();
        matchingServices = worker != null ? worker.getServices() : new ArrayList<Service>();
        return matchingServices;
    }
    
    public Iterable<ServiceSummary> findByWorkerIdDTO(Long workerId){
        ArrayList<ServiceSummary> serviceDtos = new ArrayList<ServiceSummary>();
        for(Service service : findByWorkerId(workerId)){
            serviceDtos.add(new ServiceSummary(service));
        }
        return serviceDtos;
    }

}
