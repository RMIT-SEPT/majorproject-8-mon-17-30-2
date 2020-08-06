package com.rmit.sept.majorProject.model;

import java.util.List;
import javax.persistence.*;

@Entity
public class Customer extends Person {

    private String email;
    private String address;
    private int    phoneNumber;

    protected List<Booking> bookings;

    public Customer(String name, String username, String password, 
                    String address, int phoneNumber, Business business){
        this.name = name;
        this.username = username;
        this.password = password;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.business = business;
        this.roleType = Role.CUSTOMER;
    }

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
    public int getPhoneNumber(){
        return this.phoneNumber;
    }
    public boolean setPhoneNumber(int newPhoneNumber){
        int current = this.phoneNumber;
        this.phoneNumber = newPhoneNumber;
        return (current != newPhoneNumber);
    }

}