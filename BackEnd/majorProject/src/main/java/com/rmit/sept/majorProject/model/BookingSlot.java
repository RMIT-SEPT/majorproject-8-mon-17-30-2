package com.rmit.sept.majorProject.model;

import java.util.LinkedList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class BookingSlot extends Slot {
    
    @ManyToOne
    private Service service;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bookingSlot", orphanRemoval = true)
    private List<Booking> bookings;
    @ManyToOne
    private WorkSlot workSlot;
    
    public BookingSlot(LocalDate date, LocalTime startTime, LocalTime endTime){
        this.bookings = new LinkedList<Booking>();
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public BookingSlot(){}
    
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