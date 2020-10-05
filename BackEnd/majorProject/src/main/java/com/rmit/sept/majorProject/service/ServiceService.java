package com.rmit.sept.majorProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;

import java.util.ArrayList;

import javax.validation.Valid;

import com.rmit.sept.majorProject.dto.ServiceSummary;
import com.rmit.sept.majorProject.model.Business;
import com.rmit.sept.majorProject.model.Service;
import com.rmit.sept.majorProject.model.Worker;
import com.rmit.sept.majorProject.repository.BusinessRepository;
import com.rmit.sept.majorProject.repository.ServiceRepository;
import com.rmit.sept.majorProject.repository.WorkerRepository;

@org.springframework.stereotype.Service
public class ServiceService{

	@Autowired
    private ServiceRepository repository;	
    @Autowired
    private WorkerRepository workerRepository;
    @Autowired
    private BusinessRepository businessRepository;
    
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

	public ArrayList<ServiceSummary> addServices(Long businessID, @Valid Service[] service) {
		ArrayList<Service> serviceList = new ArrayList<Service>();
		ArrayList<ServiceSummary> serviceSummaryList = new ArrayList<ServiceSummary>();
		if(!businessRepository.existsById(businessID))
		{
			throw new DataRetrievalFailureException("Invalid business ID " + businessID + " not found");
		}
		Business business = businessRepository.findById(businessID).get();
		for(Service services: service)
		{
			Service serviceTemp = new Service(services.getTitle(), services.getDescription(), services.getCapacity());
			serviceTemp.setBusiness(businessRepository.findById(businessID).get());
			serviceList.add(serviceTemp);
		}
		for(Service services: serviceList)
		{
			Service savedService = repository.save(services);
			serviceSummaryList.add(new ServiceSummary(savedService));
			business.addService(savedService);
		}
		businessRepository.save(business);
		return serviceSummaryList;
	}

}
