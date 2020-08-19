package com.rmit.sept.majorProject.model;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Business {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "business", orphanRemoval = true)
    private Admin businessOwner;
    @NotNull
    private String businessName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "business", orphanRemoval = false)
    private List<Service> services;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "business", orphanRemoval = false)
    private List<Worker>  workers;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "business", orphanRemoval = false)
    private List<Booking> bookings;

    public Business(Admin businessOwner, String businessName){
        this.services = new LinkedList<Service>();
        this.workers = new LinkedList<Worker>();
        this.bookings = new LinkedList<Booking>();
        this.businessName = businessName;       
    }

    public Business(){}

    // --------------GETTERS AND SETTERS---------------

    public long getId(){
        return this.id;
    }
    public String getBusinessName(){
        return this.businessName;
    }
    public boolean setBusinessName(String newBusinessName){
        String current = this.businessName;
        this.businessName = newBusinessName;
        return (current != newBusinessName);
    }
    public Person getBusinessOwner(){
        return this.businessOwner;
    }
    public boolean setBusinessOwner(Admin newBusinessOwner){
        long current = this.businessOwner.getId();
        this.businessOwner = newBusinessOwner;
        return (current != newBusinessOwner.getId());
    }
    public List<Service> getServices(){
        return this.services;
    }
    public List<Worker> getWorkers(){
        return this.workers;
    }
    public List<Booking> getBookings(){
        return bookings;
    }
} 