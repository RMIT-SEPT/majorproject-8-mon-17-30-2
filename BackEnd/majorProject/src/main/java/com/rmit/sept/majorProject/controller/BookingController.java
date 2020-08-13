package com.rmit.sept.majorProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.rmit.sept.majorProject.model.Booking;
import com.rmit.sept.majorProject.model.Business;
import com.rmit.sept.majorProject.model.Customer;
import com.rmit.sept.majorProject.model.Worker;
import com.rmit.sept.majorProject.service.BookingService;

@RestController
public class BookingController {

	private final BookingService bookingService;
	
	@Autowired
	public BookingController(BookingService bookingService)
	{
		this.bookingService = bookingService;
	}
	
	@GetMapping("/api/booking")
	public Iterable<Booking> getAllBookings()
	{
		return null;
	}
	
	public Iterable<Booking> getAllBookings(Business business)
	{
		return null;
	}
	
	public Iterable<Booking> getAllBookings(Worker worker)
	{
		return null;
	}
	
	public Iterable<Booking> getAllBookings(Customer customer)
	{
		return null;
	}
	
	public Booking getBooking(Long ID)
	{
		return null;
	}
	
	public Boolean addBooking(Booking booking)
	{
		return false;
	}
	
	public Boolean deleteBooking(Long ID)
	{
		return false;
	}
	
	public Boolean updateBooking(Long ID, Booking booking)
	{
		return false;
	}
}
