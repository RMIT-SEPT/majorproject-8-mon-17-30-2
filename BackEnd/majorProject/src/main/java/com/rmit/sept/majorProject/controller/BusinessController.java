package com.rmit.sept.majorProject.controller;

import com.rmit.sept.majorProject.dto.BusinessBlueprint;
import com.rmit.sept.majorProject.dto.BusinessSummary;
import com.rmit.sept.majorProject.dto.ServiceSummary;
import com.rmit.sept.majorProject.dto.WorkerServiceBlueprint;
import com.rmit.sept.majorProject.dto.WorkerSummary;

import java.util.ArrayList;

import javax.validation.Valid;

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
import com.rmit.sept.majorProject.service.BusinessService;
import com.rmit.sept.majorProject.utility.Util;

@CrossOrigin(origins = Util.API_HOST)
@RestController
public class BusinessController {

	@Autowired
	private BusinessService businessService;

	@PostMapping("api/business/{adminId}")
	public ResponseEntity<?> addBusiness(@PathVariable Long adminId, @Valid @RequestBody BusinessBlueprint business) {
		BusinessSummary businessSum;
		if (adminId < 1 || adminId == null) {
			return new ResponseEntity<String>("Invalid Admin Id", HttpStatus.BAD_REQUEST);
		}
		try {
			businessSum = businessService.addBusiness(adminId, business);
		} catch (DataRetrievalFailureException DRFE) {
			return new ResponseEntity<>(DRFE.getMessage(), HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(businessSum, HttpStatus.OK);
	}

	@GetMapping("/api/business")
	public Iterable<BusinessSummary> getAllBusinesses() {
		return businessService.getAllBusinesses();
	}

	@GetMapping("/api/business/{businessId}")
	public ResponseEntity<?> getBusiness(@PathVariable Long businessId) {
		if (businessId < 1 || businessId == null) {
			return new ResponseEntity<String>("Invalid Admin Id", HttpStatus.BAD_REQUEST);
		}
		BusinessSummary business = new BusinessSummary(businessService.findById(businessId));
		return new ResponseEntity<>(business, business != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
	}

	@GetMapping("/api/business/{businessId}/services")
	public Iterable<ServiceSummary> getServicesByBusinessId(@PathVariable Long businessId) {
		if (businessId < 1 || businessId == null) {
			return null;
		}
		return businessService.getServicesByBusinessId(businessId);
	}

	// Adds worker to the business
	@PostMapping("/api/business/{businessId}/addWorker")
	public ResponseEntity<?> addWorkerToBusiness(@Valid @RequestBody WorkerServiceBlueprint[] workerIdList,
			@PathVariable Long businessId) {
		for (WorkerServiceBlueprint workerServiceId : workerIdList) {
			if (workerServiceId.getWorkerID() < 1) {
				return new ResponseEntity<>("Invalid worker ID " + workerServiceId.getWorkerID(), HttpStatus.NOT_FOUND);
			} else if (workerServiceId.serviceIDCheck() != null) {
				return new ResponseEntity<>("Invalid service ID " + workerServiceId.serviceIDCheck(),
						HttpStatus.NOT_FOUND);
			}
		}
		if (businessId < 1 || businessId == null) {
			return new ResponseEntity<>("Invalid business ID", HttpStatus.NOT_FOUND);
		}
		ArrayList<WorkerSummary> workerList;

		try {
			workerList = businessService.addWorkerToBusiness(workerIdList, businessId);
		} catch (DataRetrievalFailureException DRFE) {
			return new ResponseEntity<>(DRFE.getMessage(), HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(workerList, HttpStatus.OK);
	}

}