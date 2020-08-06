package com.rmit.sept.majorProject.model;

import java.util.LinkedList;
import javax.persistence.*;

@Entity
public class Business {

    private long   businessID;
    private Person businessOwner;
    private String businessName;
    private LinkedList<Service> services;
    private LinkedList<Worker>  workers;

    public Business(Person businessOwner, String businessName){
        //create ID 
        this.businessOwner = businessOwner; 
        this.businessName = businessName;       
    }

    public boolean addWorker(Worker worker){
        //TODO
        return false;
    }
    public boolean removeWorker(Worker worker){
        //TODO
        return false;
    }    
    public boolean addService(Service service){
        //TODO  
        return false;      
    }
    public boolean removeService(Service service){
        //TODO
        return false;
    }

    // --------------GETTERS AND SETTERS---------------

    public long getID(){
        return this.businessID;
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
    public boolean setBusinessOwner(Person newBusinessOwner){
        long current = this.businessOwner.getID();
        this.businessOwner = newBusinessOwner;
        return (current != newBusinessOwner.getID());
    }
    public LinkedList<Service> getServices(){
        return this.services;
    }
    public LinkedList<Worker> getWorkers(){
        return this.workers;
    }
    public LinkedList<Booking> getBookings(){
        LinkedList<Booking> allBookings = new LinkedList<Booking>();
        for(Worker worker : this.workers){
            allBookings.addAll(worker.getBookings());
        }
        return allBookings;
    }
} 