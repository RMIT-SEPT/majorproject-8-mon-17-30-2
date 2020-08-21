package com.rmit.sept.majorProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import com.rmit.sept.majorProject.dto.BookingSummary;
import com.rmit.sept.majorProject.model.Booking;
import com.rmit.sept.majorProject.service.BookingService;
@CrossOrigin(origins = "http://localhost:3000")
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

//	@PostMapping("/api/booking/customer")
//	public ResponseEntity<?> getBookingsByCustomer(@RequestBody String customerUsername){
//		Iterable<Booking> matchingBookings = bookingService.getBookingsByCustomer(customerUsername);
//		//if matching bookings are found return them and Status.OK, if none, return empty list and Status.NO_CONTENT
//		return new ResponseEntity<Iterable<Booking>>(matchingBookings, matchingBookings.iterator().hasNext()? HttpStatus.OK : HttpStatus.NO_CONTENT);
//    }
	@GetMapping("/api/booking/customer/{customerUsername}")
	public ResponseEntity<?> getBookingsByCustomer(@PathVariable String customerUsername){
		Iterable<Booking> matchingBookings = bookingService.getBookingsByCustomer(customerUsername);
		//if matching bookings are found return them and Status.OK, if none, return empty list and Status.NO_CONTENT
		return new ResponseEntity<Iterable<Booking>>(matchingBookings, matchingBookings.iterator().hasNext()? HttpStatus.OK : HttpStatus.NO_CONTENT);
	}

}
