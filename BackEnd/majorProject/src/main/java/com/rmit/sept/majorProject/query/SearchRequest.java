package com.rmit.sept.majorProject.query;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.rmit.sept.majorProject.model.Business;
import com.rmit.sept.majorProject.model.Worker;

/*
 * This class is solely for the search feature in the BookingSlotController
 * in order to compartmentalise the business, worker and date objects for request.
 */
public class SearchRequest {
	
	private Business business;
	private Worker worker;
	@JsonInclude(value= JsonInclude.Include.NON_EMPTY)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-mm-dd")
	private LocalDate date;
	
	public SearchRequest(Business business, Worker worker, LocalDate date)
	{
		this.business = business;
		this.worker = worker;
		this.date = date;
	}
	
	public Business getBusiness() {
		return this.business;
	}
	
	public Worker getWorker() {
		return this.worker;
	}
	
	public LocalDate getDate() {
		return this.date;
	}
	
}
