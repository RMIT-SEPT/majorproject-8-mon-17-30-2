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
    public boolean setCustomer(Customer customer) {
    	this.customer = customer;
    	return true;
    }
    public Worker getWorker(){
        return this.worker;
    }
    public boolean setWorker(Worker worker) {
    	this.worker = worker;
    	return true;
    }
    public Business getBusiness(){
        return this.business;
    }
    public boolean setBusiness() {
    	return false;
    }
    public Service getService(){
        return this.service;
    }
    public boolean setService(Service service)
    {
    	this.service = service;
    	return true;
    }
    public BookingSlot getBookingSlot(){
        return this.bookingSlot;
    }
    public boolean setBookingSlot(Booking Slot)
    {
    	return false;
    }
   
}