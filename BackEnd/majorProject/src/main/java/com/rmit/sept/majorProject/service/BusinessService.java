package com.rmit.sept.majorProject.service;

import com.rmit.sept.majorProject.dto.ServiceSummary;
import com.rmit.sept.majorProject.dto.WorkerServiceBlueprint;
import com.rmit.sept.majorProject.dto.WorkerSummary;
import com.rmit.sept.majorProject.dto.BusinessBlueprint;
import com.rmit.sept.majorProject.dto.BusinessSummary;
import com.rmit.sept.majorProject.dto.ServiceBlueprint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;

import com.rmit.sept.majorProject.model.Admin;
import com.rmit.sept.majorProject.model.Business;
import com.rmit.sept.majorProject.model.Service;
import com.rmit.sept.majorProject.model.Worker;
import com.rmit.sept.majorProject.repository.AdminRepository;
import com.rmit.sept.majorProject.repository.BusinessRepository;
import com.rmit.sept.majorProject.repository.ServiceRepository;
import com.rmit.sept.majorProject.repository.WorkerRepository;

import java.util.ArrayList;
import java.util.Collection;

import javax.validation.Valid;

@org.springframework.stereotype.Service
public class BusinessService{

	@Autowired
	private BusinessRepository repository;	
	@Autowired
	private AdminRepository adminRepository;
	@Autowired
	private WorkerRepository workerRepository;
	@Autowired
	private ServiceRepository serviceRepository;
	@Autowired
	private BusinessRepository businessRepository;

	public BusinessSummary addBusiness(Long adminId, BusinessBlueprint business) {
		if(!adminRepository.existsById(adminId))
		{
			throw new DataRetrievalFailureException("Invalid admin ID " + adminId);
		}
		Business businessTemp = new Business(business.getBusinessName());
		for(ServiceBlueprint service : business.getServices()){
			businessTemp.addService(new Service(service.getTitle(), service.getDescription(), service.getCapacity()));
		}
		businessTemp.setBusinessOwner(adminRepository.findById(adminId).get());
		Business savedBusiness = repository.save(businessTemp);
		Admin admin = adminRepository.findById(adminId).get();
		admin.setBusiness(savedBusiness);
		adminRepository.save(admin);
		return new BusinessSummary(savedBusiness);
	}
	
	public Iterable<BusinessSummary> getAllBusinesses(){
		ArrayList<BusinessSummary> businessDtos = new ArrayList<BusinessSummary>();
		Iterable<Business> businesses = repository.findAll();
		for(Business business : businesses){
			businessDtos.add(new BusinessSummary(business));
		}
		return businessDtos;
	}

	public Business findById(Long businessId){
		return repository.findById(businessId).get();
	}

	public Iterable<ServiceSummary> getServicesByBusinessId(Long businessId) {
		ArrayList<ServiceSummary> services = new ArrayList<ServiceSummary>();
		for(Service service : repository.findById(businessId).get().getServices()){
			services.add(new ServiceSummary(service));
		}
		return services;
	}

	public ArrayList<WorkerSummary> addWorkerToBusiness(WorkerServiceBlueprint[] workerIdList, Long businessId) {
		ArrayList<Worker> workerList = new ArrayList<Worker>();
		ArrayList<WorkerSummary> workerSummaryList = new ArrayList<WorkerSummary>();
		if(!repository.existsById(businessId))
		{
			throw new DataRetrievalFailureException("Invalid business ID " + businessId + " not found");
		}
		for(WorkerServiceBlueprint workerServiceId: workerIdList)
		{
			if(workerRepository.existsById(workerServiceId.getWorkerID()) )
			{
				Worker workerTemp = workerRepository.findById(workerServiceId.getWorkerID()).get();
				workerTemp.setBusiness(repository.findById(businessId).get());
				for(Long serviceID:workerServiceId.getServiceID())
				{
					if(!serviceRepository.existsById(serviceID))
					{
						throw new DataRetrievalFailureException("Service ID " + serviceID + " is not found");
					}
					Service tempService = serviceRepository.findById(serviceID).get();
					if(!((Collection<Service>)businessRepository.findById(businessId).get().getServices()).contains(tempService))
					{
							throw new DataRetrievalFailureException("Service ID " + serviceID + " is not in business ID " + businessId);
					}
					workerTemp.addService(tempService);
				}
				workerList.add(workerTemp);
			}
			else
			{
				throw new DataRetrievalFailureException("Invalid worker ID " + workerServiceId.getWorkerID() + " not found");
			}
		}
		for(Worker worker: workerList)
		{
			workerSummaryList.add(new WorkerSummary(workerRepository.save(worker)));
		}
		return workerSummaryList;
	}
}
