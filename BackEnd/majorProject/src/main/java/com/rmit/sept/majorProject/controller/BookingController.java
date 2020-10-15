package com.rmit.sept.majorProject.controller;

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

import com.rmit.sept.majorProject.dto.BookingBlueprint;
import com.rmit.sept.majorProject.dto.BookingSummary;
import com.rmit.sept.majorProject.service.BookingService;
import com.rmit.sept.majorProject.utility.Util;

@CrossOrigin(origins = Util.API_HOST)
@RestController
public class BookingController {

	@Autowired
	private BookingService bookingService;

	@GetMapping("/api/booking")
	public Iterable<BookingSummary> getAllBookings() {
		return bookingService.getAllBookingsDTO();
	}

	@PostMapping("/api/booking")
	public ResponseEntity<?> addBooking(@Valid @RequestBody BookingBlueprint blueprint) {
		if (blueprint.getCustomerId() == null || blueprint.getCustomerId() <= 0 || blueprint.getWorkerId() == null
				|| blueprint.getWorkerId() <= 0 || blueprint.getBusinessId() == null || blueprint.getBusinessId() <= 0
				|| blueprint.getBookingSlotId() == null || blueprint.getBookingSlotId() <= 0
				|| blueprint.getServiceId() == null || blueprint.getServiceId() <= 0) {
			return new ResponseEntity<String>("Invalid ID(s) passed", HttpStatus.BAD_REQUEST);
		}
		BookingSummary booking;
		try {
			booking = this.bookingService.createNewBooking(blueprint);
		} catch (DuplicateKeyException dkEx) {
			return new ResponseEntity<String>(dkEx.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(booking, HttpStatus.CREATED);
	}

	@DeleteMapping("/api/booking/{bookingId}")
	public ResponseEntity<?> cancelBooking(@PathVariable Long id) {
		if (id == null || id <= 0) {
			return new ResponseEntity<String>("Invalid ID passed", HttpStatus.BAD_REQUEST);
		}
		Boolean result = this.bookingService.cancelBooking(id);
		return new ResponseEntity<>(result ? HttpStatus.OK : HttpStatus.NOT_FOUND);
	}

	// ---------------------CUSTOMER BOOKING API----------------------

	@GetMapping("/api/customer/{customerId}/bookings/past")
	public ResponseEntity<?> getPastBookingsByCustomerIdDTO(@PathVariable Long customerId) {
		if (customerId == null || customerId <= 0) {
			return new ResponseEntity<String>("Invalid ID passed", HttpStatus.BAD_REQUEST);
		}
		Iterable<BookingSummary> matchingBookings = bookingService.getPastBookingsByCustomerIdDTO(customerId);
		// if matching bookings are found return them and Status.OK, if none, return
		// empty list and Status.NO_CONTENT
		return new ResponseEntity<>(matchingBookings,
				matchingBookings.iterator().hasNext() ? HttpStatus.OK : HttpStatus.NO_CONTENT);
	}

	@GetMapping("/api/customer/{customerId}/bookings/current")
	public ResponseEntity<?> getCurrentBookingsByCustomerIdDTO(@PathVariable Long customerId) {
		if (customerId == null || customerId <= 0) {
			return new ResponseEntity<String>("Invalid ID passed", HttpStatus.BAD_REQUEST);
		}
		Iterable<BookingSummary> matchingBookings = bookingService.getCurrentBookingsByCustomerIdDTO(customerId);
		// if matching bookings are found return them and Status.OK, if none, return
		// empty list and Status.NO_CONTENT
		return new ResponseEntity<>(matchingBookings,
				matchingBookings.iterator().hasNext() ? HttpStatus.OK : HttpStatus.NO_CONTENT);
	}

	@GetMapping("/api/customer/{customerId}/bookings")
	public ResponseEntity<?> getBookingsByCustomerId(@PathVariable Long customerId) {
		if (customerId == null || customerId <= 0) {
			return new ResponseEntity<String>("Invalid ID passed", HttpStatus.BAD_REQUEST);
		}
		Iterable<BookingSummary> matchingBookings = bookingService.findByCustomerIdDTO(customerId);
		// if matching bookings are found return them and Status.OK, if none, return
		// empty list and Status.NO_CONTENT
		return new ResponseEntity<>(matchingBookings,
				matchingBookings.iterator().hasNext() ? HttpStatus.OK : HttpStatus.NO_CONTENT);
	}

	@GetMapping("/api/business/{businessId}/bookings/past")
	public ResponseEntity<?> getPastBookingsByBusiness(@PathVariable Long businessId) {
		if (businessId == null || businessId <= 0) {
			return new ResponseEntity<String>("Invalid ID passed", HttpStatus.BAD_REQUEST);
		}
		Iterable<BookingSummary> bookings = bookingService.getPastBookingsByBusinessIdDTO(businessId);
		return new ResponseEntity<>(bookings, bookings.iterator().hasNext() ? HttpStatus.OK : HttpStatus.NO_CONTENT);
	}

	@GetMapping("/api/business/{businessId}/bookings/new")
	public ResponseEntity<?> getNewBookingsByBusiness(@PathVariable Long businessId) {
		if (businessId == null || businessId <= 0) {
			return new ResponseEntity<String>("Invalid ID passed", HttpStatus.BAD_REQUEST);
		}
		Iterable<BookingSummary> bookings = bookingService.getNewBookingsByBusinessIdDTO(businessId);
		return new ResponseEntity<>(bookings, bookings.iterator().hasNext() ? HttpStatus.OK : HttpStatus.NO_CONTENT);
	}

	@GetMapping("/api/newest/{number}/bookings")
	public ResponseEntity<?> getNewestBookings(@PathVariable int number) {
		if (number <= 0) {
			return new ResponseEntity<String>("Invalid number of bookings passed", HttpStatus.BAD_REQUEST);
		}
		Iterable<BookingSummary> bookings = bookingService.getNewestBookings(number);
		return new ResponseEntity<>(bookings, bookings.iterator().hasNext() ? HttpStatus.OK : HttpStatus.NO_CONTENT);
	}
}
