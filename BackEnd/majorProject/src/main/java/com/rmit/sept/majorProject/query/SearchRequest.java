package com.rmit.sept.majorProject.query;

import java.time.LocalDate;

/*
 * This class is solely for the search feature in the BookingSlotController
 * in order to compartmentalise the business, worker and date objects for request.
 */
public class SearchRequest {
	
	private Long businessId;
	private Long serviceId;
	private Long workerId;
	private String dateString;
	private LocalDate date;
	
	public SearchRequest(){
	}
	
	public SearchRequest(Long businessId, Long serviceId, Long workerId, String dateString){
		this.businessId = businessId;
		this.serviceId = serviceId;
		this.workerId = workerId;
		this.dateString = dateString;
		if(dateString != ""){
			this.date = LocalDate.parse(dateString);
		}		
	}	
	
	public Long getBusinessId() {
		return this.businessId;
	}

	public Long getServiceId() {
		return this.serviceId;
	}
	
	public Long getWorkerId() {
		return this.workerId;
	}
	
	public LocalDate getDate() {
		return this.date;
	}

	public void setBusinessId(Long businessId) {
		this.businessId = businessId;
	}

	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
	}

	public void setWorkerId(Long workerId) {
		this.workerId = workerId;
	}

	public String getDateString() {
		return dateString;
	}

	public void setDateString(String dateString) {
		this.dateString = dateString;
		if(dateString != ""){
			this.date = LocalDate.parse(dateString);	
		}		
	}
	
}
