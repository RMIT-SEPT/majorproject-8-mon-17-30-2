package com.rmit.sept.majorProject.model;

import java.util.LinkedList;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Min;
import javax.validation.constraints.Max;

@Entity
public class Worker extends Person {
    
    @NotNull
    private String email;
    @NotNull
    private String address;
    @Min(5)
    @Max(10)
    private String phoneNumber;

    @ManyToOne
    private Business business;
    @ManyToMany
    private List<Service>  services;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "worker", orphanRemoval = true)
    private List<WorkSlot> shifts;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "worker", orphanRemoval = true)
    private List<Booking> bookings;

    public Worker(String name, String username, String password,
                  String address, String phoneNumber, Business business){
        this.name = name;
        this.username = username;
        this.password = password;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.business = business;
        this.roleType = Role.WORKER;
        this.services = new LinkedList<Service>();
        this.shifts = new LinkedList<WorkSlot>();
        this.bookings = new LinkedList<Booking>();
    }

    public Worker(){}

    // --------------GETTERS AND SETTERS---------------

    public String getEmail(){
        return this.email;
    }
    public boolean setEmail(String newEmail){
        String current = this.email;
        this.email = newEmail;
        return (current != newEmail);
    }    
    public String getAddress(){
        return this.address;
    }
    public boolean setAddress(String newAddress){
        String current = this.address;
        this.address = newAddress;
        return (current != newAddress);
    }
    public String getPhoneNumber(){
        return this.phoneNumber;
    }
    public boolean setPhoneNumber(String newPhoneNumber){
        String current = this.phoneNumber;
        this.phoneNumber = newPhoneNumber;
        return (current != newPhoneNumber);
    }
    public List<Service> getServices(){
        return this.services;
    }
    public List<WorkSlot> getWorkingHours(){
        return this.shifts;
    }
    public List<Booking> getBookings(){
        return this.bookings;
    }
    /* business getter/setter re-used in Customer and Worker since Hibernate 
    doesn't really like one-to-many when dealing with inheritance/abstraction,
    so the business object can't be inherited */
    public Business getBusiness(){
        return this.business;
    }
    public boolean setBusiness(Business newBusiness){
        long current = this.business.getId();
        this.business = newBusiness;
        return (current != newBusiness.getId());
    }
    public boolean addService(Service service){
        //TODO
        return false; 
    }
    public boolean removeService(Service service){
        //TODO
        return false;
    }
    public boolean addShift(Slot shift){
        //TODO
        return false;
    }
}