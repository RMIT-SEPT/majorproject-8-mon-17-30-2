package com.rmit.sept.majorProject.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import com.rmit.sept.majorProject.model.Booking;
import com.rmit.sept.majorProject.model.BookingSlot;
import com.rmit.sept.majorProject.model.Service;

public class BookingSlotSummary{

    private Long id;
    private String businessName;
    private Long workerId;
    private String workerName;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private Long workSlotId;
    private boolean isSet;
    private Service bookedService;
    private Iterable<Service> availableServices;
    private boolean fullyBooked;
    private ArrayList<BookingSummary> bookings = new ArrayList<BookingSummary>();

    public BookingSlotSummary(BookingSlot bookingSlot){
        this.id = bookingSlot.getId();
        this.businessName = bookingSlot.getWorkSlot().getWorker().getBusiness().getBusinessName();
        this.workerId = bookingSlot.getWorkSlot().getWorker().getId();
        this.workerName = bookingSlot.getWorkSlot().getWorker().getName();
        this.date = bookingSlot.getDate();
        this.startTime = bookingSlot.getStartTime();
        this.endTime = bookingSlot.getEndTime();
        this.workSlotId = bookingSlot.getWorkSlot().getId();
        this.isSet = bookingSlot.isSet();
        this.bookedService = bookingSlot.getBookedService();
        this.availableServices = bookingSlot.getAvailableServices();
        this.fullyBooked = bookingSlot.fullyBooked();
        for(Booking booking : bookingSlot.getBookings()){
            this.bookings.add(new BookingSummary(booking));
        }
    }

    public Long getId() {
        return id;
    }

    public Long getWorkerId() {
        return workerId;
    }

    public String getWorkerName(){
        return this.workerName;
    }

    public LocalDate getDate(){
        return this.date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public Long getWorkSlotId() {
        return workSlotId;
    }

    public boolean isSet() {
        return isSet;
    }

    public Service getBookedService() {
        return bookedService;
    }

    public Iterable<Service> getAvailableServices() {
        return availableServices;
    }
   
    public boolean isFullyBooked(){
        return this.fullyBooked;
    }

    public String getBusinessName() {
        return businessName;
    }

    public Iterable<BookingSummary> getBookings() { 
        return this.bookings;
    }

}