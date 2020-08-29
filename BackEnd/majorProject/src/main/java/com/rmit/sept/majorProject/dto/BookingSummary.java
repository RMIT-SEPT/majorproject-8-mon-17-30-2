package com.rmit.sept.majorProject.dto;

import java.time.LocalTime;
import com.rmit.sept.majorProject.model.Booking;

public class BookingSummary {

    private Long id;
    private Long customerId;
    private String customerName;
    private Long workerId;
    private String workerName;
    private LocalTime startTime;
    private LocalTime endTime;
    private Long bookingSlotId;
    private Long serviceId;
    private String serviceTitle;

    public BookingSummary(Booking booking){
        this.id = booking.getBookingId();
        this.customerId = booking.getCustomer().getId();
        this.customerName = booking.getCustomer().getName();
        this.workerId = booking.getWorker().getId();
        this.workerName = booking.getWorker().getName();
        this.startTime = booking.getBookingSlot().getStartTime();
        this.endTime = booking.getBookingSlot().getEndTime();
        this.bookingSlotId = booking.getBookingSlot().getId();
        this.serviceId = booking.getService().getId();
        this.serviceTitle = booking.getService().getTitle();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Long getWorkerId() {
        return workerId;
    }

    public void setWorkerId(Long workerId) {
        this.workerId = workerId;
    }

    public String getWorkerName() {
        return workerName;
    }

    public void setWorkerName(String workerName) {
        this.workerName = workerName;
    }

    public Long getBookingSlotId() {
        return bookingSlotId;
    }

    public void setBookingSlotId(Long bookingSlotId) {
        this.bookingSlotId = bookingSlotId;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceTitle() {
        return serviceTitle;
    }

    public void setServiceTitle(String serviceTitle) {
        this.serviceTitle = serviceTitle;
    }

}