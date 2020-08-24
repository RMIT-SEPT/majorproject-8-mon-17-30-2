package com.rmit.sept.majorProject.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class BookingSlot extends Slot {

    private Long slotID;
    @ManyToOne
    private Service service;

    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "bookingSlot", orphanRemoval = true)
    private List<Booking> bookings = new ArrayList<Booking>();

    @ManyToOne
    private WorkSlot workSlot;
    
    public BookingSlot(LocalDate date, LocalTime startTime, LocalTime endTime, Service service){
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.service = service;
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

    public java.time.LocalDate getBookSlotDate() {
        return this.date;
    }

    public void setWorkSlot(WorkSlot newSlot) {
        //prevent endless loop
        if (sameAsFormer(newSlot))
            return ;
        //set new owner
        WorkSlot oldSlot = this.workSlot;
        this.workSlot = newSlot;
        //remove from the old workslot
        if (oldSlot!=null)
            oldSlot.removeBookingSlot(this);
        //set myself into new owner
        if (workSlot!=null)
            workSlot.addBookingSlot(this);
        }
    
        private boolean sameAsFormer(WorkSlot newSlot) {
            return workSlot==null? newSlot == null : workSlot.equals(newSlot);
        }
    
}