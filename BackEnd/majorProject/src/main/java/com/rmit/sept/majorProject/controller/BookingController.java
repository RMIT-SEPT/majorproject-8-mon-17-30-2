package com.rmit.sept.majorProject.controller;

import java.time.LocalDate;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.yaml.snakeyaml.constructor.DuplicateKeyException;

import com.rmit.sept.majorProject.dto.BookingSummary;
import com.rmit.sept.majorProject.model.Booking;
import com.rmit.sept.majorProject.model.Business;
import com.rmit.sept.majorProject.model.Worker;
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
    public ResponseEntity<?> addBooking(@Valid @RequestBody Booking booking){
		BookingSummary booking1;
    	try {
    		booking1 = this.bookingService.createNewBooking(booking);
    	}
    	catch (DuplicateKeyException dkEx) {
    		return new ResponseEntity<String>(dkEx.getMessage(), HttpStatus.BAD_REQUEST);
    	}
    	return new ResponseEntity<>(booking1, HttpStatus.CREATED);
	}

	@DeleteMapping("/api/booking/{id}")
    public ResponseEntity<?> removeBooking(@Valid @RequestBody Long id, Booking booking){
    	// if(result.hasErrors()){
    	// 	return new ResponseEntity<>("Invalid Booking Object", HttpStatus.BAD_REQUEST);
    	// }
		
		// Booking booking1 = this.bookingService.removeExistingBooking(id);
		this.bookingService.removeExistingBooking(id);
    	return new ResponseEntity<>(HttpStatus.RESET_CONTENT);
	}

	//---------------------CUSTOMER BOOKING API----------------------

	@GetMapping("/api/customer/{customerId}/bookings/past")
	public ResponseEntity<?> getPastBookingsByCustomerIdDTO(@PathVariable Long customerId) {
		Iterable<BookingSummary> matchingBookings = bookingService.getPastBookingsByCustomerIdDTO(customerId);
		//if matching bookings are found return them and Status.OK, if none, return empty list and Status.NO_CONTENT
		return new ResponseEntity<>(matchingBookings, matchingBookings.iterator().hasNext() ? HttpStatus.OK : HttpStatus.NO_CONTENT);
	}

	@GetMapping("/api/customer/{customerId}/bookings/current")
	public ResponseEntity<?> getCurrentBookingsByCustomerIdDTO(@PathVariable Long customerId) {
		Iterable<BookingSummary> matchingBookings = bookingService.getCurrentBookingsByCustomerIdDTO(customerId);
		//if matching bookings are found return them and Status.OK, if none, return empty list and Status.NO_CONTENT
		return new ResponseEntity<>(matchingBookings, matchingBookings.iterator().hasNext() ? HttpStatus.OK : HttpStatus.NO_CONTENT);
	}

	@GetMapping("/api/customer/{customerId}/bookings")
	public ResponseEntity<?> getBookingsByCustomerId(@PathVariable Long customerId) {
		Iterable<BookingSummary> matchingBookings = bookingService.findByCustomerIdDTO(customerId);
		//if matching bookings are found return them and Status.OK, if none, return empty list and Status.NO_CONTENT
		return new ResponseEntity<>(matchingBookings, matchingBookings.iterator().hasNext() ? HttpStatus.OK : HttpStatus.NO_CONTENT);
	}
	@GetMapping("/api/business/{businessId}/bookings/past")
	public ResponseEntity<?> getPastBookingsByBusiness(@PathVariable Long businessId)
	{
		Iterable<BookingSummary> bookings = bookingService.getPastBookingsByBusinessIdDTO(businessId);
		return new ResponseEntity<>(bookings, bookings.iterator().hasNext() ? HttpStatus.OK : HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/api/business/{business}/bookings")
	public ResponseEntity<?> getAvailableBookingsByBusiness(@PathVariable("business") Business business)
	{
		Iterable<Booking> bookings = bookingService.getAvailableBookingsByBusiness(business);
		return new ResponseEntity<>(bookings, bookings.iterator().hasNext() ? HttpStatus.OK : HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/api/worker/{worker}/bookings")
	public ResponseEntity<?> getAvailableBookingsByWorker(@PathVariable("worker") Worker worker)
	{
		Iterable<Booking> bookings = bookingService.getAvailableBookingsByWorker(worker);
		return new ResponseEntity<>(bookings, bookings.iterator().hasNext() ? HttpStatus.OK : HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/api/day/{day}/bookings")
	public ResponseEntity<?> getAvailableBookingsByDay(@PathVariable("day") LocalDate day)
	{
		Iterable<Booking> bookings = bookingService.getAvailableBookingsByDay(day);
		return new ResponseEntity<>(bookings, bookings.iterator().hasNext() ? HttpStatus.OK : HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/api/newest/{number}/bookings")
	public ResponseEntity<?> getNewestBookings(@PathVariable("number") int number)
	{
		Iterable<BookingSummary> bookings = bookingService.getNewestBookings(number);
		return new ResponseEntity<>(bookings,bookings.iterator().hasNext() ? HttpStatus.OK : HttpStatus.NO_CONTENT);
	}
	
//	@PostMapping("/api/booking/customer")
//	public ResponseEntity<?> getBookingsByCustomer(@RequestBody String customerUsername){
//		Iterable<Booking> matchingBookings = bookingService.getBookingsByCustomer(customerUsername);
//		//if matching bookings are found return them and Status.OK, if none, return empty list and Status.NO_CONTENT
//		return new ResponseEntity<Iterable<Booking>>(matchingBookings, matchingBookings.iterator().hasNext()? HttpStatus.OK : HttpStatus.NO_CONTENT);
//	}

}
