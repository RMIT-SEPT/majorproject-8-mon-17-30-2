package com.rmit.sept.majorProject.dto;

import java.time.LocalTime;
import com.rmit.sept.majorProject.model.Booking;

public class BookingSummary {

    private Long id;
    private Long customer;
    private Long worker;
    private LocalTime startTime;
    private LocalTime endTime;
    private Long bookingSlot;
    private Long service;

    public BookingSummary(Booking booking){
        this.id = booking.getBookingId();
        this.customer = booking.getCustomer().getId();
        this.worker = booking.getWorker().getId();
        this.startTime = booking.getBookingSlot().getStartTime();
        this.endTime = booking.getBookingSlot().getEndTime();
        this.bookingSlot = booking.getBookingSlot().getSlotID();
        this.service = booking.getService().getId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomer() {
        return customer;
    }

    public void setCustomer(Long customer) {
        this.customer = customer;
    }

    public Long getWorker() {
        return worker;
    }

    public void setWorker(Long worker) {
        this.worker = worker;
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

    public Long getBookingSlot() {
        return bookingSlot;
    }

    public void setBookingSlot(Long bookingSlot) {
        this.bookingSlot = bookingSlot;
    }

    public Long getService() {
        return service;
    }

    public void setService(Long service) {
        this.service = service;
    }

}