package com.rmit.sept.majorProject.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class WorkSlot extends Slot {

    @ManyToOne
    private Worker worker;

    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "workSlot", orphanRemoval = true)
    private List<BookingSlot> bookingSlots = new ArrayList<BookingSlot>();
    
    public WorkSlot(LocalDate date, LocalTime startTime, LocalTime endTime, Worker worker){
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.worker = worker;
    }

    public WorkSlot(){
    }
    
    // --------------GETTERS AND SETTERS---------------

    public void addBookingSlot(BookingSlot newSlot) {
        //prevent endless loop
        if (bookingSlots.contains(newSlot))
            return ;
        //add new booking slot
        bookingSlots.add(newSlot);
        //set this as bookingslot's parent
        newSlot.setWorkSlot(this);
    }

    public void removeBookingSlot(BookingSlot slotToRemove) {
        //prevent endless loop
        if (!bookingSlots.contains(slotToRemove))
            return ;
        //remove the booking slot
        bookingSlots.remove(slotToRemove);
        //remove this as bookingslot's parent
        slotToRemove.setWorkSlot(null);
    }

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
    public void setWorker(Worker worker){
        this.worker = worker;
    }
    
}