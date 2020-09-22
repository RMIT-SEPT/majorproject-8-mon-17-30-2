package com.rmit.sept.majorProject.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.rmit.sept.majorProject.dto.BookingSlotSummary;
import com.rmit.sept.majorProject.model.BookingSlot;
import com.rmit.sept.majorProject.query.SearchRequest;
import com.rmit.sept.majorProject.service.BookingSlotService;

@CrossOrigin(origins = "http://agmemonday2.com.s3-website-us-east-1.amazonaws.com")
@RestController
public class BookingSlotController {

	@Autowired
	private BookingSlotService bookingSlotService;
	
	@GetMapping("/api/booking-slot")
	public Iterable<BookingSlotSummary> getAllBookingSlots() {
		return bookingSlotService.getAllBookingSlotsDTO();
	}

	@GetMapping("/api/booking-slot/{id}")
	public ResponseEntity<?> getBookingSlot(@PathVariable Long id){
		BookingSlotSummary summary = new BookingSlotSummary(bookingSlotService.findById(id));
		return new ResponseEntity<>(summary, summary != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/api/booking-slot/available")
	public Iterable<BookingSlotSummary> getAvailableBookingSlots() {
		return bookingSlotService.getAvailableBookingSlotsDTO();
	}

	@PostMapping("/api/booking-slot/search")
	public ResponseEntity<?> searchAvailableBookingSlots(@Valid @RequestBody SearchRequest search){
		Iterable<BookingSlotSummary> availableBookingSlots = bookingSlotService.searchAvailableBookingSlots(search.getBusinessId(), search.getServiceId(), search.getWorkerId(), search.getDate());
		return new ResponseEntity<>(availableBookingSlots, availableBookingSlots.iterator().hasNext() ? HttpStatus.OK : HttpStatus.NO_CONTENT);
	}

	@GetMapping("/api/booking-slot/newest")
	public BookingSlotSummary getNewest(){
		return new BookingSlotSummary(bookingSlotService.getNewest());
	}
}
