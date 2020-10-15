package com.rmit.sept.majorProject.dto;

import java.util.ArrayList;
import java.util.List;
import com.rmit.sept.majorProject.model.Booking;
import com.rmit.sept.majorProject.model.Customer;

public class CustomerSummary {

    private Long   id;
    private String name;
    private String email;
    private String username;
    private String password;
    private String address;
    private String phoneNumber;
    private List<Long> bookings = new ArrayList<Long>();

    public CustomerSummary(Customer customer) {
        this.id = customer.getId();
        this.name = customer.getName();
        for (Booking booking : customer.getBookings()) {
            this.bookings.add(booking.getBookingId());
        }
        this.email = customer.getEmail();
        this.username = customer.getUsername();
        this.password = customer.getPassword();
        this.address = customer.getAddress();
        this.phoneNumber = customer.getPhoneNumber();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Iterable<Long> getBookings() {
        return this.bookings;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPassword() {
        return password;
    }

}