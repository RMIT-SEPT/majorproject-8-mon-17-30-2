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

    @NotNull
    private String businessName;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "business", orphanRemoval = false)
    private Admin businessOwner;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "business", orphanRemoval = false)
    private List<Service> services = new ArrayList<Service>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "business", orphanRemoval = false)
    private List<Worker>  workers = new ArrayList<Worker>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "worker", orphanRemoval = true)
    private List<WorkSlot> workSlots = new ArrayList<WorkSlot>();

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
    //Used to generate mock-id for testing purposes
    public void setId(Long id) {
        this.id = id;
    }

    public String getBusinessName(){
        return this.businessName;
    }

    public void setBusinessName(String newBusinessName){
        this.businessName = newBusinessName;
    }
    
    public Admin getBusinessOwner(){
        return this.businessOwner;
    }

    public void setBusinessOwner(Admin newBusinessOwner){
        if(this.businessOwner != null && this.businessOwner.equals(newBusinessOwner)){
            return;
        }
        this.businessOwner = newBusinessOwner;
        newBusinessOwner.setBusiness(this);
    }

    public void addWorker(Worker worker) {
        if(workers.contains(worker)){
            return ;
        }
        workers.add(worker);
        worker.setBusiness(this);
    }

    public void removeWorker(Worker worker) {
        if(!workers.contains(worker)){
            return ;
        }
        workers.remove(worker);
        worker.setBusiness(null);
    }

    public List<Worker> getWorkers(){
        return this.workers;
    }

    public void addBooking(Booking booking) {
        if (bookings.contains(booking))
            return;
        bookings.add(booking);
        booking.setBusiness(this);
    }
    
    public void removeBooking(Booking booking) {
        if (!bookings.contains(booking))
            return ;
        bookings.remove(booking);
        booking.setBusiness(null);
    }

    public List<Booking> getBookings(){
        return bookings;
    }

    public void addService(Service service) {
        if (services.contains(service))
            return;
        services.add(service);
        service.setBusiness(this);
    }

    public void removeService(Service service) {
        if (!services.contains(service))
            return ;
        services.remove(service);
        service.setBusiness(null);
    }

    public List<Service> getServices(){
        return this.services;
    }

    @Override
    public String toString() {
        return "Business{" +
                "id=" + id +
                ", businessName='" + businessName + '\'' +
                '}';
    }
}