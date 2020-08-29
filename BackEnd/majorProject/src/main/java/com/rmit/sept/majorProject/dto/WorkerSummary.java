package com.rmit.sept.majorProject.dto;

import java.util.ArrayList;
import java.util.List;
import com.rmit.sept.majorProject.model.Booking;
import com.rmit.sept.majorProject.model.Service;
import com.rmit.sept.majorProject.model.Worker;

public class WorkerSummary {

    private Long id;
    private String name;
    private String email;
    private String username;
    private String phoneNumber;
    private String address;
    private List<ServiceSummary> services = new ArrayList<ServiceSummary>();
    private List<Long> bookings = new ArrayList<Long>();

    public WorkerSummary(Worker worker){
        this.id = worker.getId();
        this.name = worker.getName();
        for(Booking booking : worker.getBookings()){
            this.bookings.add(booking.getBookingId());
        }
        for(Service service : worker.getServices()){
            this.services.add(new ServiceSummary(service));
        }
        this.email = worker.getEmail();
        this.username = worker.getUsername();
        this.phoneNumber = worker.getPhoneNumber();
        this.address = worker.getAddress();
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

    public Iterable<ServiceSummary> getServices() {
        return this.services;        
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}