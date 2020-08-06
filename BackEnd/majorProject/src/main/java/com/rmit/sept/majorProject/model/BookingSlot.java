package com.rmit.sept.majorProject.model;

import java.util.List;
import javax.persistence.Entity;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class BookingSlot extends Slot {
    
    private Service service;
    private List<Booking> bookings;
    
    public BookingSlot(LocalDate date, LocalTime startTime, LocalTime endTime){
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }
    
    // --------------GETTERS AND SETTERS---------------
    
    public Service getService(){
        return this.service;
    }
    public boolean setService(Service newService){
        Service current = this.service;
        this.service = newService;
        return (current != newService);
    }
    public List<Booking> getBookings(){
        return this.bookings;
    }
    public boolean fullyBooked(){
        return(this.bookings.size() >= service.getCapacity());
    }
    
}