package com.rmit.sept.majorProject.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class WorkSlot extends Slot {

    @ManyToOne
    private Worker worker;    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "workSlot", orphanRemoval = false)
    private List<BookingSlot> bookingSlots;
    
    public WorkSlot(LocalDate date, LocalTime startTime, LocalTime endTime){
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public WorkSlot(){}
    
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