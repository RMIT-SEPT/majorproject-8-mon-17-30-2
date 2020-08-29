package com.rmit.sept.majorProject.dto;

import java.time.LocalTime;
import com.rmit.sept.majorProject.model.BookingSlot;
import com.rmit.sept.majorProject.model.Service;

public class BookingSlotSummary{

    private Long id;
    private Long workerId;
    private String workerName;
    private LocalTime startTime;
    private LocalTime endTime;
    private Long workSlotId;
    private boolean isSet;
    private Service bookedService;
    private Iterable<Service> availableServices;
    private boolean fullyBooked;

    public BookingSlotSummary(BookingSlot bookingSlot){
        this.id = bookingSlot.getId();
        this.workerId = bookingSlot.getWorkSlot().getWorker().getId();
        this.workerName = bookingSlot.getWorkSlot().getWorker().getName();
        this.startTime = bookingSlot.getStartTime();
        this.endTime = bookingSlot.getEndTime();
        this.workSlotId = bookingSlot.getWorkSlot().getId();
        this.isSet = bookingSlot.isSet();
        this.bookedService = bookingSlot.getBookedService();
        this.availableServices = bookingSlot.getAvailableServices();
        this.fullyBooked = bookingSlot.fullyBooked();
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

}