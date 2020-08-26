package com.rmit.sept.majorProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import com.rmit.sept.majorProject.dto.BookingSlotSummary;
import com.rmit.sept.majorProject.model.BookingSlot;
import com.rmit.sept.majorProject.service.BookingSlotService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class BookingSlotController {

	@Autowired
	private BookingSlotService bookingSlotService;
	
    @GetMapping("/api/booking-slot")
	public Iterable<BookingSlotSummary> getAllBookingSlots() {
		ArrayList<BookingSlotSummary> bookingSlotDtos = new ArrayList<>();
		Iterable<BookingSlot> bookingSlots = bookingSlotService.getAllAvailable();
		for(BookingSlot bookingSlot : bookingSlots){
			bookingSlotDtos.add(new BookingSlotSummary(bookingSlot));
		}
		return bookingSlotDtos;
	}

}
