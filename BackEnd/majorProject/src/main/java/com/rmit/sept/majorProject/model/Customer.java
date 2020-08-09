package com.rmit.sept.majorProject.model;

import java.util.LinkedList;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.Size;

import org.springframework.lang.NonNull;

@Entity
public class Customer extends Person {

    @NonNull
    private String email;
    @NonNull
    private String address;
    @Size(min=5, max=10)
    private String phoneNumber;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer", orphanRemoval = true)
    protected List<Booking> bookings;
    @ManyToOne
    private Business business;

    public Customer(String name, String username, String password, 
                    String address, String email, String phoneNumber){
        this.name = name;
        this.username = username;
        this.password = password;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.roleType = Role.CUSTOMER;
        this.bookings = new LinkedList<Booking>();
    }

    public Customer(){}

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
    /* business getter/setter re-used in Admin and Worker since Hibernate 
    doesn't really like one-to-many when dealing with inheritance/abstraction */
    public Business getBusiness(){
        return this.business;
    }
    public boolean setBusiness(Business newBusiness){
        long current = this.business.getId();
        this.business = newBusiness;
        return (current != newBusiness.getId());
    }

}