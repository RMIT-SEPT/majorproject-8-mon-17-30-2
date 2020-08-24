package com.rmit.sept.majorProject.model;

import javax.persistence.*;

@Entity
public class Booking {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long        id;

    @ManyToOne
    private Customer    customer;

    @ManyToOne
    private Worker      worker;
    
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Business    business;

    @ManyToOne
    private Service     service;
    
    @ManyToOne
    private BookingSlot bookingSlot;

    public Booking(Customer customer, Worker worker, Business business,
                   Service service, BookingSlot bookingSlot){
        this.customer = customer;
        this.worker = worker;
        this.business = business;
        this.service = service;    
        this.bookingSlot = bookingSlot;               
    }

    public Booking(){}

    // --------------GETTERS AND SETTERS---------------
    
    public Long getBookingId(){
    	return this.id;
    }
    public Customer getCustomer(){
        return this.customer;
    }
    public boolean setCustomer(Customer customer){
    	this.customer = customer;
    	return true;
    }
    public Worker getWorker(){
        return this.worker;
    }
    public boolean setWorker(Worker worker){
    	this.worker = worker;
    	return true;
    }
    public Business getBusiness(){
        return this.business;
    }
    public boolean setBusiness(Business business){
    	this.business = business;
    	return true;
    }
    public Service getService(){
        return this.service;
    }
    public boolean setService(Service service){
    	this.service = service;
    	return true;
    }
    public BookingSlot getBookingSlot(){
        return this.bookingSlot;
    }
    public boolean setBookingSlot(BookingSlot bookingSlot){
        this.bookingSlot = bookingSlot;
    	return false;
    }
   
}