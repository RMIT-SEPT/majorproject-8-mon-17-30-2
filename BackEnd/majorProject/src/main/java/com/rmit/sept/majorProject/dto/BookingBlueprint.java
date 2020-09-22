package com.rmit.sept.majorProject.dto;

public class BookingBlueprint {

    private Long customerId;
    private Long workerId;
    private Long businessId;
    private Long bookingSlotId;
    private Long serviceId;

    public BookingBlueprint(){        
    }

    public BookingBlueprint(Long customerId, Long workerId, Long businessId,
                            Long bookingSlotId, Long serviceId) {
        this.customerId = customerId;
        this.workerId = workerId;
        this.businessId = businessId;
        this.bookingSlotId = bookingSlotId;
        this.serviceId = serviceId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getWorkerId() {
        return workerId;
    }

    public void setWorkerId(Long workerId) {
        this.workerId = workerId;
    }

    public Long getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Long businessId) {
        this.businessId = businessId;
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

}