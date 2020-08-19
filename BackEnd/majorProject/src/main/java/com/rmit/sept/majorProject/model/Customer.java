package com.rmit.sept.majorProject.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Customer extends Person {

    @Email
    private String email;

    @NotNull
    @NotEmpty
    private String address;

    @Size(min=5, max=10)
    private String phoneNumber;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer", orphanRemoval = true)
    private List<Booking> bookings = new ArrayList<Booking>();

    @ManyToOne
    private Business business;

    public Customer(){
        this.role = Role.CUSTOMER;
    }

    public Customer(String name, String username, String password, 
                    String address, String email, String phoneNumber){
        this.name = name;
        this.username = username;
        this.password = password;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.role = Role.CUSTOMER;
    }

    //copy constructor
    public Customer(Customer other){
        this.name = other.getName();
        this.username = other.getUsername();
        this.password = other.getPassword();
        this.email = other.getEmail();
        this.address = other.getAddress();
        this.phoneNumber = other.getPhoneNumber();
        this.business = other.getBusiness();
        this.role = Role.CUSTOMER;
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
    /* business getter/setter re-used in Admin and Worker since Hibernate 
    doesn't really like one-to-many when dealing with inheritance/abstraction */
    public Business getBusiness(){
        return this.business;
    }
    public void setBusiness(Business newBusiness){
        this.business = newBusiness;
    }
    public Iterable<Booking> getBookings(){
        return this.bookings;
    }

}