package com.rmit.sept.majorProject.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import com.rmit.sept.majorProject.dto.BookingSummary;
import com.rmit.sept.majorProject.model.Booking;
import com.rmit.sept.majorProject.service.BookingService;

@RestController
public class BookingController {

	@Autowired
	private BookingService bookingService;
	
    @GetMapping("/api/booking")
	public Iterable<BookingSummary> getAllBookings() {
		ArrayList<BookingSummary> bookingDtos = new ArrayList<BookingSummary>();
		Iterable<Booking> bookings = bookingService.getAllBookings();
		for(Booking booking : bookings){
			bookingDtos.add(new BookingSummary(booking));
		}
		return bookingDtos;
	}
    
    @PostMapping("/api/booking/")
    public ResponseEntity<?> addBooking(@Valid @RequestBody Booking booking, BindingResult result)
    {
    	if(result.hasErrors())
    	{
    		return new ResponseEntity<String>("Invalid Booking Object", HttpStatus.BAD_REQUEST);
    	}
		Booking booking1 = this.bookingService.createNewBooking(booking);
    	return new ResponseEntity<Booking>(booking, HttpStatus.CREATED);
    }
    
	@PostMapping("/api/booking/customer")
	public ResponseEntity<?> getBookingsByCustomer(@RequestBody String customerUsername){	    
		Iterable<Booking> matchingBookings = bookingService.getBookingsByCustomer(customerUsername);
		//if matching bookings are found return them and Status.OK, if none, return empty list and Status.NO_CONTENT
		return new ResponseEntity<Iterable<Booking>>(matchingBookings, matchingBookings.iterator().hasNext()? HttpStatus.OK : HttpStatus.NO_CONTENT);
    }
}
