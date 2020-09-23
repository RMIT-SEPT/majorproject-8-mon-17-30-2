package com.rmit.sept.majorProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import com.rmit.sept.majorProject.dto.ServiceSummary;
import com.rmit.sept.majorProject.model.Service;
import com.rmit.sept.majorProject.service.ServiceService;
@CrossOrigin(origins = "http://localhost:3000")
// @CrossOrigin(origins = "http://agmemonday2.com.s3-website-us-east-1.amazonaws.com")
@RestController
public class ServiceController{
	
	@Autowired
	private ServiceService serviceService;

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