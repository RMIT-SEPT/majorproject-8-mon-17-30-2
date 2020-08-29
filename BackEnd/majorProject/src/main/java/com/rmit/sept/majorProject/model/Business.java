package com.rmit.sept.majorProject.model;

import java.util.ArrayList;
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

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "business", orphanRemoval = false)
    private Admin businessOwner;

    @NotNull
    private String businessName;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "business", orphanRemoval = false)
    private List<Service> services = new ArrayList<Service>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "business", orphanRemoval = false)
    private List<Worker>  workers = new ArrayList<Worker>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "business", orphanRemoval = false)
    private List<Booking> bookings = new ArrayList<Booking>();

    public Business(String businessName){
        this.businessName = businessName;       
    }

    public Business(){}

    // --------------GETTERS AND SETTERS---------------

    public Long getId(){
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