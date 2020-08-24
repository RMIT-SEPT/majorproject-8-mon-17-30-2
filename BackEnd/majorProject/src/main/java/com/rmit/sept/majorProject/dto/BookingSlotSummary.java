package com.rmit.sept.majorProject.dto;

import java.time.LocalTime;
import com.rmit.sept.majorProject.model.BookingSlot;
import com.rmit.sept.majorProject.model.Service;

public class BookingSlotSummary {

    private Long id;
    private Long workerId;
    private String workerName;
    private LocalTime startTime;
    private LocalTime endTime;
    private Long workSlotId;
    private boolean isSet;
    private Service bookedService;
    private Iterable<Service> availableServices;

    public BookingSlotSummary(BookingSlot bookingSlot){
        this.id = bookingSlot.getId();
        this.workerId = bookingSlot.getWorkSlot().getWorker().getId();
        this.startTime = bookingSlot.getStartTime();
        this.endTime = bookingSlot.getEndTime();
        this.workSlotId = bookingSlot.getWorkSlot().getId();
        this.isSet = bookingSlot.getBookedService() != null;
        this.bookedService = bookingSlot.getBookedService();
        this.availableServices = bookingSlot.getAvailableServices();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getWorkerId() {
        return workerId;
    }

    public void setWorkerId(Long workerId) {
        this.workerId = workerId;
    }

    public String getWorkerName(){
        return this.workerName;
    }

    public void setWorkerName(String workerName){
        this.workerName = workerName;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public Long getWorkSlotId() {
        return workSlotId;
    }

    public void setWorkSlotId(Long workSlotId) {
        this.workSlotId = workSlotId;
    }

    public boolean isSet() {
        return isSet;
    }

    public void setSet(boolean isSet) {
        this.isSet = isSet;
    }

    public Service getBookedService() {
        return bookedService;
    }

    public void setBookedService(Service bookedService) {
        this.bookedService = bookedService;
    }

    public Iterable<Service> getAvailableServices() {
        return availableServices;
    }

    public void setAvailableServices(Iterable<Service> availableServices) {
        this.availableServices = availableServices;
    }    

}