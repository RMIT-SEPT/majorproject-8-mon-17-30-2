package com.rmit.sept.majorProject.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class BookingSlot extends Slot {
    
    @ManyToMany
    private List<Service> availableServices = new ArrayList<Service>();

    @ManyToOne
    private Service bookedService;

    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "bookingSlot", orphanRemoval = true)
    private List<Booking> bookings = new ArrayList<Booking>();

    @ManyToOne
    private WorkSlot workSlot;
    
    public BookingSlot(LocalDate date, LocalTime startTime, LocalTime endTime, List<Service> services){
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.availableServices = services;
    }

    public BookingSlot(){}
    
    // --------------GETTERS AND SETTERS---------------
    
    public Iterable<Service> getAvailableServices(){
        return this.availableServices;
    }

    public void addService(Service newService){
        if(!availableServices.contains(newService)){
            this.availableServices.add(newService);
        }
    }

    public void removeService(Service service){
        availableServices.remove(service);
    }

    public void addBooking(Booking booking){
        this.bookings.add(booking);
        if(this.bookedService == null){
            setBookedService(booking.getService());
        }
    }

    public Service getBookedService(){
        return this.bookedService;
    }

    public void setBookedService(Service service){
        this.bookedService = service;
    }

    public void removeBookedService(){
        this.bookedService = null;
    }

    public List<Booking> getBookings(){
        return this.bookings;
    }

    public WorkSlot getWorkSlot(){
        return this.workSlot;
    }

    public boolean fullyBooked(){
        return(this.bookings.size() >= bookedService.getCapacity());
    }

    public void setWorkSlot(WorkSlot newSlot) {
        //prevent endless loop
        if(sameAsFormer(newSlot)){
            return;
        }
        //set new owner
        WorkSlot oldSlot = this.workSlot;
        this.workSlot = newSlot;
        //remove from the old workslot
        if (oldSlot!=null){
            oldSlot.removeBookingSlot(this);
        }
        //set myself into new owner
        if (workSlot!=null){
            workSlot.addBookingSlot(this);
        }
    }
    
    private boolean sameAsFormer(WorkSlot newSlot) {
        return workSlot==null? newSlot == null : workSlot.equals(newSlot);
    }

    public boolean isSet(){
        return this.bookedService != null;
    }

    public void setAvailableServices(List<Service> availableServices) {
        this.availableServices = availableServices;
    }
    
}