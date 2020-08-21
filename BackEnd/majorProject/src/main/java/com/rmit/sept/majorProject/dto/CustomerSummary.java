package com.rmit.sept.majorProject.dto;

import java.util.ArrayList;
import java.util.List;
import com.rmit.sept.majorProject.model.Booking;
import com.rmit.sept.majorProject.model.Customer;

public class CustomerSummary {

    private Long id;
    private String name;
    private List<Long> bookings = new ArrayList<Long>();

    public CustomerSummary(Customer customer){
        this.id = customer.getId();
        this.name = customer.getName();
        for(Booking booking : customer.getBookings()){
            this.bookings.add(booking.getBookingId());
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Iterable<Long> getBookings() {
        return this.bookings;        
    }

}