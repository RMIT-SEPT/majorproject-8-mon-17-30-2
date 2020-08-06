package com.rmit.sept.majorProject.model;

import java.util.LinkedList;
import javax.persistence.*;

@Entity
public class Worker extends Person {
    
    private String email;
    private String address;
    private int    phoneNumber;

    private LinkedList<Service>  services;
    private LinkedList<WorkSlot> shifts;

    public Worker(String name, String username, String password,
                  String address, int phoneNumber, Business business){
        this.name = name;
        this.username = username;
        this.password = password;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.business = business;
        this.roleType = Role.WORKER;
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
    public LinkedList<Service> getServices(){
        return this.services;
    }
    public LinkedList<WorkSlot> getWorkingHours(){
        return this.shifts;
    }
    public LinkedList<Booking> getBookings(){
        LinkedList<Booking> allBookings = new LinkedList<Booking>();
        for(WorkSlot shift : this.shifts){
            for(BookingSlot bookingSlot : shift.getBookingSlots()){
                allBookings.addAll(bookingSlot.getBookings());
            }                
        }
        return allBookings;
    }
}