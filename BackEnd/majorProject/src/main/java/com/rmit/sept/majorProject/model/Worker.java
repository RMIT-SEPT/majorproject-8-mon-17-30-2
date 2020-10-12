package com.rmit.sept.majorProject.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.rmit.sept.majorProject.dto.WorkerBlueprint;

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

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(name = "worker_services",
        joinColumns = @JoinColumn(name = "worker_id"),
        inverseJoinColumns = @JoinColumn(name = "service_id"))
    private List<Service>  services = new ArrayList<Service>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "worker", orphanRemoval = true)
    private List<WorkSlot> workSlots = new ArrayList<WorkSlot>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "worker", orphanRemoval = true)
    private List<Booking> bookings = new ArrayList<Booking>();

    public Worker(){
        this.role = Role.WORKER;
    }

    //manual constructor for testing
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

    //blueprint constructor
    public Worker(WorkerBlueprint other){
        this.name = other.getName();
        this.address = other.getAddress();
        this.phoneNumber = other.getPhoneNumber();
        this.email = other.getEmail();
        this.username = other.getUsername();
        this.password = other.getPassword();
        this.role = Role.WORKER;
    }

    // --------------PERSONAL---------------

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

    //---------------SERVICES--------------

    //add service
    public void addService(Service service){
        this.services.add(service);
        // service.addWorker(this);
    }

    public void removeService(Service service){
        if(!services.contains(service)){
            return;
        }
        services.remove(service);
        // service.removeWorker(this);
    }

    public void removeServices(){
        for(Service serviceout : this.services){
            this.removeService(serviceout);
        }            
    }

    public void setServices(Iterable<Service> services){
        removeServices();
        for(Service servicein : services){
            addService(servicein);
        }
    }

    public Iterable<Service> getServices(){
        return this.services;
    } 
    
    //---------------WORKSLOTS---------------
    
    public void addWorkSlot(WorkSlot workSlot) {
        if (workSlots.contains(workSlot))
            return;
        workSlots.add(workSlot);
        workSlot.setWorker(this);
    }

    public void removeWorkSlot(WorkSlot workSlot) {
        if(!workSlots.contains(workSlot)){
            return;
        }
        workSlots.remove(workSlot);
        workSlot.setWorker(null);
    }

    public List<WorkSlot> getWorkSlots(){
        return this.workSlots;
    }

    //----------------BOOKINGS---------------

    public void addBooking(Booking booking) {
        if (bookings.contains(booking))
            return;
        bookings.add(booking);
        booking.setWorker(this);
    }

    public void removeBooking(Booking booking) {
        if(!bookings.contains(booking)){
            return;
        }
        bookings.remove(booking);
        booking.setWorker(null);
    }

    public List<Booking> getBookings(){
        return this.bookings;
    }

    //----------------BUSINESS----------------

    public Business getBusiness(){
        return this.business;
    }
    
    public void setBusiness(Business newBusiness) {
        //prevent endless loop
        if(sameAsFormer(newBusiness)){
            return;
        }
        //set new owner
        Business oldBusiness = this.business;
        this.business = newBusiness;
        //remove from the old business
        if(oldBusiness!=null){
            oldBusiness.removeWorker(this);
        }
        //set myself into new owner
        if(newBusiness!=null){
            newBusiness.addWorker(this);
        }
    }

    //-----------------UTIL--------------------

    private boolean sameAsFormer(Business newBusiness) {
        return this.business==null ? newBusiness == null : this.business.equals(newBusiness);
    }


    @Override
    public String toString() {
        return "Worker{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}