package com.rmit.sept.majorProject.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Booking {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long        id;
    @ManyToOne
    private Customer    customer;
    @ManyToOne
    @JsonBackReference
    private Worker      worker;
    @ManyToOne
    private Business    business;
    @ManyToOne
    private Service     service;
    @ManyToOne
    private BookingSlot bookingSlot;

    public Booking(Customer customer, Worker worker, Business business,
                   Service service){
        this.customer = customer;
        this.worker = worker;
        this.business = business;
        this.service = service;                   
    }

    public Booking(){}

    // --------------GETTERS AND SETTERS---------------
    
    public Long getBookingId() {
    	return this.id;
    }
    public Customer getCustomer(){
        return this.customer;
    }
    public Worker getWorker(){
        return this.worker;
    }
    public Business getBusiness(){
        return this.business;
    }
    public Service getService(){
        return this.service;
    }
    public BookingSlot getBookingSlot(){
        return this.bookingSlot;
    }
   
}