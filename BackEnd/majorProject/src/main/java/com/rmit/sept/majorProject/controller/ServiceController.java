package com.rmit.sept.majorProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;

import javax.validation.Valid;

import com.rmit.sept.majorProject.dto.ServiceSummary;
import com.rmit.sept.majorProject.model.Service;
import com.rmit.sept.majorProject.service.ServiceService;
import com.rmit.sept.majorProject.Util;

@CrossOrigin(origins = Util.API_HOST)
@RestController
public class ServiceController{
	
	@Autowired
	private ServiceService serviceService;

	@PostMapping("/api/service/{businessId}/add")
	public ResponseEntity<?> addService(@PathVariable Long businessId, @Valid @RequestBody Service[] service)
	{
		if(businessId <1)
		{
			return new ResponseEntity<String>("Invalid Business Id",HttpStatus.BAD_REQUEST);
		}
		for(Service services: service)
		{
			if(services.getTitle() == null ||
					services.getDescription() == null||
					services.getCapacity() < 1)
			{
				return new ResponseEntity<String>("Invalid service", HttpStatus.BAD_REQUEST);
			}
		}
		ArrayList<ServiceSummary> serviceList;
		try {
			serviceList = serviceService.addServices(businessId, service);
		}
		catch(DataRetrievalFailureException DRFE)
		{
			return new ResponseEntity<>(DRFE.getMessage(), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(serviceList, serviceList.isEmpty() ? HttpStatus.NO_CONTENT: HttpStatus.CREATED);
	}
	
	public Iterable<Service> getAllServices() {
		return serviceService.findAll();
	}
	
	@GetMapping("/api/service/{serviceId}")
	public ResponseEntity<?> findByIdDTO(@PathVariable Long serviceId){
		ServiceSummary service = new ServiceSummary(serviceService.findById(serviceId));
		return new ResponseEntity<>(service, service != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
	}
    
    public Iterable<Service> findByBusinessId(Long businessId){
        return serviceService.findByBusinessId(businessId);
    }
    
    public Iterable<ServiceSummary> findByBusinessIdDTO(Long businessId){
        ArrayList<ServiceSummary> serviceDtos = new ArrayList<ServiceSummary>();
		Iterable<Service> services = findByBusinessId(businessId);
		for(Service service : services){
			serviceDtos.add(new ServiceSummary(service));
		}
		return serviceDtos;
    }	
	
	@GetMapping("/api/worker/{workerId}/services")
	public ResponseEntity<?> findByWorkerIdDTO(@PathVariable Long workerId){
		Iterable<ServiceSummary> services = serviceService.findByWorkerIdDTO(workerId);
		return new ResponseEntity<>(services, services.iterator().hasNext() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
	}

}