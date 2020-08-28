package com.rmit.sept.majorProject.service;

import java.util.ArrayList;
import com.rmit.sept.majorProject.dto.BookingSlotSummary;
import com.rmit.sept.majorProject.model.BookingSlot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rmit.sept.majorProject.repository.BookingSlotRepository;

@Service
public class BookingSlotService{

	@Autowired
    private BookingSlotRepository repository;

    // return a list of all booking slot objects, whether or not they contain a booking
    public Iterable<BookingSlot> getAllBookingSlots(){
		return repository.findAll();
    }

    public Iterable<BookingSlotSummary> getAllBookingSlotsDTO(){
        ArrayList<BookingSlotSummary> bookingSlotDtos = new ArrayList<>();
		Iterable<BookingSlot> bookingSlots = getAllBookingSlots();
		for(BookingSlot bookingSlot : bookingSlots){
			bookingSlotDtos.add(new BookingSlotSummary(bookingSlot));
        }
        return bookingSlotDtos;
    }
    
    //return list of all bookingslots that either:
    // 1: are unset (no booking has been created, and thus no service has been "set")
    // 2: have vacancy (booking(s) have been created, service has been set, but the capacity hasn't been reached)
    public Iterable<BookingSlot> getAvailableBookingSlots(){

        ArrayList<BookingSlot> availableSlots = new ArrayList<BookingSlot>();
        for(BookingSlot slot : repository.findAll()){
            if(!slot.isSet() || slot.getBookings().size() < slot.getBookedService().getCapacity()){
                availableSlots.add(slot);
            }
        }
        
        return availableSlots;
    }

    public Iterable<BookingSlotSummary> getAvailableBookingSlotsDTO(){
        ArrayList<BookingSlotSummary> availableSlotDtos = new ArrayList<BookingSlotSummary>();
        for(BookingSlot slot : getAvailableBookingSlots()){
            availableSlotDtos.add(new BookingSlotSummary(slot));
        }
        return availableSlotDtos;
    }

}
