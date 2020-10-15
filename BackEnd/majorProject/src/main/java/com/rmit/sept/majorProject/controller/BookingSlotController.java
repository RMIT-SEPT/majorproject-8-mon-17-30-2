package com.rmit.sept.majorProject.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.rmit.sept.majorProject.dto.BookingSlotBlueprint;
import com.rmit.sept.majorProject.dto.BookingSlotSummary;
import com.rmit.sept.majorProject.dto.SearchRequest;
import com.rmit.sept.majorProject.service.BookingSlotService;
import com.rmit.sept.majorProject.utility.Util;

@CrossOrigin(origins = Util.API_HOST)
@RestController
public class BookingSlotController {

	@Autowired
	private BookingSlotService bookingSlotService;

	@GetMapping("/api/booking-slot")
	public Iterable<BookingSlotSummary> getAllBookingSlots() {
		return bookingSlotService.getAllBookingSlotsDTO();
	}

	@PostMapping("/api/work-slot/{workSlotId}/booking-slot")
	public ResponseEntity<?> addNewWorkSlot(@PathVariable Long workSlotId,
			@Valid @RequestBody BookingSlotBlueprint blueprint) {
		if (workSlotId <= 0 || blueprint.getWorkSlotId() <= 0 || blueprint.getWorkSlotId() == null
				|| blueprint.getDate() == null || blueprint.getStartTime() == null || blueprint.getEndTime() == null
				|| blueprint.getServiceIds() == null) {
			return new ResponseEntity<String>("Invalid value(s) passed", HttpStatus.BAD_REQUEST);
		}
		BookingSlotSummary bookingSlot;
		try {
			bookingSlot = this.bookingSlotService.createNewBookingSlot(blueprint);
		} catch (DuplicateKeyException DkEx) {
			return new ResponseEntity<String>(DkEx.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (DataIntegrityViolationException DIVEx) {
			return new ResponseEntity<String>(DIVEx.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}
		return new ResponseEntity<>(bookingSlot, HttpStatus.CREATED);
	}

	@GetMapping("/api/booking-slot/{id}")
	public ResponseEntity<?> getBookingSlot(@PathVariable Long id) {
		if (id == null || id <= 0) {
			return new ResponseEntity<String>("Invalid ID passed", HttpStatus.BAD_REQUEST);
		}
		BookingSlotSummary summary = new BookingSlotSummary(bookingSlotService.findById(id));
		return new ResponseEntity<>(summary, summary != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
	}

	@GetMapping("/api/booking-slot/available")
	public Iterable<BookingSlotSummary> getAvailableBookingSlots() {
		return bookingSlotService.getAvailableBookingSlotsDTO();
	}

	// Business Availability, Returns list of BookingSlots by BusinessId for next 7
	// days
	@GetMapping("/api/booking-slot/available/{businessId}")
	public ResponseEntity<?> getBusinessAvailability(@PathVariable Long businessId) {
		if (businessId == null || businessId <= 0) {
			return new ResponseEntity<String>("Invalid ID passed", HttpStatus.BAD_REQUEST);
		}
		Iterable<BookingSlotSummary> availability = bookingSlotService.getBusinessAvailabilityDTO(businessId);
		return new ResponseEntity<>(availability,
				availability.iterator().hasNext() ? HttpStatus.OK : HttpStatus.NO_CONTENT);
	}

	//Able to search dynamically based on what variables are passed (i.e. some variables can be left null)
	@PostMapping("/api/booking-slot/search")
	public ResponseEntity<?> searchAvailableBookingSlots(@Valid @RequestBody SearchRequest search) {
		Iterable<BookingSlotSummary> availableBookingSlots = bookingSlotService.searchAvailableBookingSlots(
				search.getBusinessId(), search.getServiceId(), search.getWorkerId(), search.getDate());
		return new ResponseEntity<>(availableBookingSlots,
				availableBookingSlots.iterator().hasNext() ? HttpStatus.OK : HttpStatus.NO_CONTENT);
	}

	@GetMapping("/api/booking-slot/newest")
	public BookingSlotSummary getNewest() {
		return new BookingSlotSummary(bookingSlotService.getNewest());
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/api/booking-slot/{bookingSlotId}")
	public ResponseEntity<?> updateBookingSlot(@Valid @RequestBody BookingSlotBlueprint newBookingSlot,
			@PathVariable Long bookingSlotId) {
		if (bookingSlotId <= 0 || 
			newBookingSlot.getWorkSlotId() <= 0 || 
			newBookingSlot.getWorkSlotId() == null ||
			newBookingSlot.getStartTime() == null || 
			newBookingSlot.getEndTime() == null || 
			newBookingSlot.getServiceIds() == null) {
				return new ResponseEntity<String>("Invalid value(s) passed", HttpStatus.BAD_REQUEST);
		}
		BookingSlotSummary bookingSlot;
		try {
			bookingSlot = bookingSlotService.editBookingSlot(bookingSlotId, newBookingSlot);
		} catch (DuplicateKeyException DkEx) {
			return new ResponseEntity<String>(DkEx.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (DataIntegrityViolationException DIVEx) {
			return new ResponseEntity<String>(DIVEx.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}
		return new ResponseEntity<>(bookingSlot, HttpStatus.CREATED);
	}

	@DeleteMapping(value = "/api/booking-slot/{bookingSlotId}")
	public ResponseEntity<?> deleteBookingSlot(@PathVariable Long bookingSlotId) {
		if (bookingSlotId == null || bookingSlotId <= 0) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		boolean isRemoved = bookingSlotService.deleteBookingSlot(bookingSlotId);

		if (!isRemoved) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(bookingSlotId, HttpStatus.OK);
	}

}
