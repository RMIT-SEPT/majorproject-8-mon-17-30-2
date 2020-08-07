package com.rmit.sept.majorProject.model;

import javax.persistence.*;

@Entity
public class Booking {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long        id;
    private Customer    customer;
    private Worker      worker;
    private Business    business;
    private Service     service;
    private BookingSlot timeSlot;

    public Booking(Customer customer, Worker worker, Business business,
                   Service service, BookingSlot timeSlot){
        this.customer = customer;
        this.worker = worker;
        this.business = business;
        this.service = service;
        this.timeSlot = timeSlot;                   
    }
    
    public Long getBookingID() {
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
    public BookingSlot getTimeSlot(){
        return this.timeSlot;
    }
   
}