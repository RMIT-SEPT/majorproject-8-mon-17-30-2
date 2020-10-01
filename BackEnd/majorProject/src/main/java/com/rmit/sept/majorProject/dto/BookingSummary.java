package com.rmit.sept.majorProject.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import com.rmit.sept.majorProject.model.Booking;
import com.rmit.sept.majorProject.model.Booking.Status;

public class BookingSummary implements DateTime{

    private Long id;
    private Long customerId;
    private String customerName;
    private Long workerId;
    private String workerName;
    private Long businessId;
    private String businessName;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private Long bookingSlotId;
    private Long serviceId;
    private String serviceTitle;
    private Status status;
    

    public BookingSummary(Booking booking){
        this.id = booking.getBookingId();
        this.customerId = booking.getCustomer().getId();
        this.customerName = booking.getCustomer().getName();
        this.workerId = booking.getWorker().getId();
        this.workerName = booking.getWorker().getName();
        this.businessId = booking.getBusiness().getId();
        this.businessName = booking.getBusiness().getBusinessName();
        this.date = booking.getBookingSlot().getDate();
        this.startTime = booking.getBookingSlot().getStartTime();
        this.endTime = booking.getBookingSlot().getEndTime();
        this.bookingSlotId = booking.getBookingSlot().getId();
        this.serviceId = booking.getService().getId();
        this.serviceTitle = booking.getService().getTitle();
        this.status = booking.getStatus();
    }

    public Long getId() {
        return id;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public Long getWorkerId() {
        return workerId;
    }

    public String getWorkerName() {
        return workerName;
    }

    public Long getBookingSlotId() {
        return bookingSlotId;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public String getServiceTitle() {
        return serviceTitle;
    }

    public LocalDate getDate() {
        return date;
    }

    public Long getBusinessId(){
        return this.businessId;
    }

    public String getBusinessName() {
        return businessName;
    }
    
    public Status getStatus() {
    	return this.status;
    }
    
    public boolean equals(BookingSummary summary)
    {
    	if(this.getCustomerId() == summary.getCustomerId()
    			&& this.getWorkerId() == summary.getWorkerId()
    			&& this.getBookingSlotId() == summary.getBookingSlotId()
    			&& this.getServiceId() == summary.getServiceId()
    			&& this.getBusinessId() == summary.getBusinessId())
    	{
    		return true;
    	}
    	return false;
    }
    
    @Override
    public boolean equals(Object o)
    {
    	return equals((BookingSummary) o);
    }

}