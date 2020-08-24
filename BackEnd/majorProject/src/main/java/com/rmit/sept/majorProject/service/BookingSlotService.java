package com.rmit.sept.majorProject.service;

import java.util.ArrayList;
import com.rmit.sept.majorProject.model.BookingSlot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rmit.sept.majorProject.repository.BookingSlotRepository;

@Service
public class BookingSlotService{

	@Autowired
    private BookingSlotRepository repository;

    //return list of all bookingslots that either:
    // 1: are unset (no booking has been created, and thus no service has been "set")
    // 2: have vacancy (booking(s) have been created, service has been set, but the capacity hasn't been reached)
    public Iterable<BookingSlot> getAllAvailable(){
        Iterable<BookingSlot> allSlots = repository.findAll();
        ArrayList<BookingSlot> availableSlots = new ArrayList<BookingSlot>();
        for(BookingSlot slot : allSlots){
            if(!slot.isSet() || slot.getBookings().size() < slot.getBookedService().getCapacity()){
                availableSlots.add(slot);
            }
        }
        return availableSlots;
    }


}
