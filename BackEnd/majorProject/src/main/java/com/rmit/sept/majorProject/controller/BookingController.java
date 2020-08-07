package com.rmit.sept.majorProject.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/book")
public class BookingController {
	private final BookingService bookingService;
	
	@Autowired
	public BookingController(BookingSerive bookingService)
	{
		this.bookingService = bookingService;
	}
	
	public List<Booking> getAllBookings()
	{
		return null;
	}
	
	public List<Booking> getAllBookings(Business business)
	{
		return null;
	}
	
	public List<Booking> getAllBookings(Worker worker)
	{
		return null;
	}
	
	public List<Booking> getAllBookings(Customer customer)
	{
		return null;
	}
	
	public Person getBooking(Long ID)
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
