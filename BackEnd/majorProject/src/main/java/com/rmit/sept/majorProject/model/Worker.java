package com.rmit.sept.majorProject.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Email;

@Entity
public class Worker extends Person {
    
    @Email
    private String email;

    @NotNull
    @NotEmpty
    private String address;

    @Size(min=5, max=10)
    private String phoneNumber;

    @ManyToOne
    private Business business;

    @ManyToMany
    private List<Service>  services;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "worker", orphanRemoval = true)
    private List<WorkSlot> workSlots = new ArrayList<WorkSlot>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "worker", orphanRemoval = true)
    private List<Booking> bookings = new ArrayList<Booking>();

    public Worker(){
        this.role = Role.WORKER;
    }

    public Worker(String name, String username, String password,
                  String email, String address, String phoneNumber){
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.role = Role.WORKER;
    }

    //copy constructor
    public Worker(Worker other){
        this.name = other.getName();
        this.username = other.getUsername();
        this.password = other.getPassword();
        this.email = other.getEmail();
        this.address = other.getAddress();
        this.phoneNumber = other.getPhoneNumber();
        this.business = other.getBusiness();
        this.role = Role.WORKER;
    }

    // --------------GETTERS AND SETTERS---------------

    public String getEmail(){
        return this.email;
    }
    public void setEmail(String newEmail){
        this.email = newEmail;
    }    
    public String getAddress(){
        return this.address;
    }
    public void setAddress(String newAddress){
        this.address = newAddress;
    }
    public String getPhoneNumber(){
        return this.phoneNumber;
    }
    public void setPhoneNumber(String newPhoneNumber){
        this.phoneNumber = newPhoneNumber;
    }
    public List<Service> getServices(){
        return this.services;
    }
    public void setServices(List<Service> services){
        this.services = services;
    }
    public List<WorkSlot> getWorkSlots(){
        return this.workSlots;
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
    public void setBusiness(Business newBusiness){
        this.business = newBusiness;
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