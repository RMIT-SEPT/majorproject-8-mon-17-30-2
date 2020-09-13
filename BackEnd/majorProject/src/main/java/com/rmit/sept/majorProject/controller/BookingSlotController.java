package com.rmit.sept.majorProject.controller;

import java.time.LocalDate;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.rmit.sept.majorProject.dto.BookingSlotSummary;
import com.rmit.sept.majorProject.model.Business;
import com.rmit.sept.majorProject.model.Worker;
import com.rmit.sept.majorProject.query.SearchRequest;
import com.rmit.sept.majorProject.service.BookingSlotService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class BookingSlotController {

	@Autowired
	private BookingSlotService bookingSlotService;
	
	@GetMapping("/api/booking-slot")
	public Iterable<BookingSlotSummary> getAllBookingSlots() {
		return bookingSlotService.getAllBookingSlotsDTO();
	}
	
	@GetMapping("/api/booking-slot/available")
	public Iterable<BookingSlotSummary> getAvailableBookingSlots() {
		return bookingSlotService.getAvailableBookingSlotsDTO();
	}


	@PostMapping("/api/search/booking-slot")
	public ResponseEntity<?> searchAvailableBookingSlots(@Valid @RequestBody  SearchRequest search){
		Iterable<BookingSlotSummary> availableBookingSlots = bookingSlotService.searchAvailableBookingSlots(search.getBusiness(), search.getWorker(), search.getDate());
		return new ResponseEntity<>(availableBookingSlots, availableBookingSlots.iterator().hasNext() ? HttpStatus.OK : HttpStatus.NO_CONTENT);
	}
}
