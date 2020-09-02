package com.rmit.sept.majorProject.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
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
		return bookingService.getAllBookingsDTO();
	}

	@PostMapping("/api/booking")
    public ResponseEntity<?> addBooking(@Valid @RequestBody Booking booking, BindingResult result){
    	if(result.hasErrors()){
    		return new ResponseEntity<>("Invalid Booking Object", HttpStatus.BAD_REQUEST);
    	}
		Booking booking1 = this.bookingService.createNewBooking(booking);
    	return new ResponseEntity<>(booking1, HttpStatus.CREATED);
	}

	//---------------------CUSTOMER BOOKING API----------------------

	@GetMapping("/api/customer/{customerId}/bookings/past")
	public ResponseEntity<?> getPastBookingsByCustomerIdDTO(@PathVariable Long customerId) {
		Iterable<BookingSummary> matchingBookings = bookingService.getPastBookingsByCustomerIdDTO(customerId);
		//if matching bookings are found return them and Status.OK, if none, return empty list and Status.NO_CONTENT
		return new ResponseEntity<>(matchingBookings, matchingBookings.iterator().hasNext() ? HttpStatus.OK : HttpStatus.NO_CONTENT);
	}

	@GetMapping("/api/customer/{customerId}/bookings")
	public ResponseEntity<?> getBookingsByCustomerId(@PathVariable Long customerId) {
		Iterable<BookingSummary> matchingBookings = bookingService.findByCustomerIdDTO(customerId);
		//if matching bookings are found return them and Status.OK, if none, return empty list and Status.NO_CONTENT
		return new ResponseEntity<>(matchingBookings, matchingBookings.iterator().hasNext() ? HttpStatus.OK : HttpStatus.NO_CONTENT);
	}

}