package com.rmit.sept.majorProject.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import javax.persistence.Entity;

@Entity
public class WorkSlot extends Slot {
    
    private List<BookingSlot> bookingSlots;
    
    public WorkSlot(LocalDate date, LocalTime startTime, LocalTime endTime){
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    // --------------GETTERS AND SETTERS---------------

    public LocalDate getDate(){
        return this.date;
    }
    public boolean setDate(LocalDate newDate){
        LocalDate current = this.date;
        this.date = newDate;
        return (current != newDate);
    }
    public List<BookingSlot> getBookingSlots(){
        return this.bookingSlots;
    }
    
}