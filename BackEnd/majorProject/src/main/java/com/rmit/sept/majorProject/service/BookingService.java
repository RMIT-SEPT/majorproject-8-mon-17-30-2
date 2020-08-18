package com.rmit.sept.majorProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rmit.sept.majorProject.model.Booking;
import com.rmit.sept.majorProject.repository.BookingRepository;

@Service
public class BookingService{

	@Autowired
	private BookingRepository repository;
	
	public Iterable<Booking> getAllBookings(){
		return repository.findAll();
	}

	public Iterable<Booking> getBookingsByCustomer(String customerUsername){
		return repository.findByCustomerUsername(customerUsername);
	}

	public Iterable<Booking> getBookingsByWorker(String workerUsername){
		return repository.findByWorkerUsername(workerUsername);
	}

}
