package com.rmit.sept.majorProject.model;

import java.time.LocalDate;

import javax.persistence.*;

@Entity
public class Booking implements Comparable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long        id;

    @ManyToOne
    private Customer    customer;

    @ManyToOne
    private Worker      worker;
    
    @ManyToOne
    private Business    business;

    @ManyToOne
    private Service     service;

    @ManyToOne
    private BookingSlot bookingSlot;

    public enum Status{
    	BOOKED,
    	CANCELLED,
    	COMPLETED
    }
    
    public Status status;
    
    public Booking(Customer customer, Worker worker, Business business,
                   Service service, BookingSlot bookingSlot){
        this.customer = customer;
        this.worker = worker;
        this.business = business;
        this.service = service;    
        this.bookingSlot = bookingSlot;       
        this.status = Status.BOOKED;
    }

    public Booking(){
    	this.status = Status.BOOKED;
    }

    //--------------GETTERS AND SETTERS---------------
    
    public Long getBookingId(){
    	return this.id;
    }
    public void setBookingId(Long id) {
    	this.id = id;
    }
    public Customer getCustomer(){
        return this.customer;
    }
    public boolean setCustomer(Customer customer){
    	this.customer = customer;
    	return true;
    }
    public Worker getWorker(){
        return this.worker;
    }
    public boolean setWorker(Worker worker){
    	this.worker = worker;
    	return true;
    }
    public Business getBusiness(){
        return this.business;
    }

    public void setBusiness(Business newBusiness) {
        if(sameAsFormer(newBusiness)){
            return;
        }
        Business oldBusiness = this.business;
        this.business = newBusiness;
        if (oldBusiness!=null){
            oldBusiness.removeBooking(this);
        }
        if(newBusiness!=null){
            newBusiness.addBooking(this);
        }
    }

    private boolean sameAsFormer(Business newBusiness) {
        return this.business==null ? newBusiness == null : this.business.equals(newBusiness);
    }

    public Service getService(){
        return this.service;
    }
    public boolean setService(Service service){
    	this.service = service;
    	return true;
    }
    public BookingSlot getBookingSlot(){
        return this.bookingSlot;
    }
    public boolean setBookingSlot(BookingSlot bookingSlot){
        this.bookingSlot = bookingSlot;
    	return false;
    }
    
    public boolean equals(Booking booking)
    {
    	if(this.getCustomer().getId() == booking.getCustomer().getId()
    			&& this.getBusiness().getId() == booking.getBusiness().getId()
    			&& this.getBookingSlot().getId() == booking.getBookingSlot().getId()
    			&& this.getWorker().getId() == booking.getWorker().getId()
    			&& this.getService().getId() == booking.getService().getId())
    	{
    		return true;
    	}
    	return false;
    }
    
    public void setStatusCompleted() {
    	if(this.bookingSlot.getDate().compareTo(LocalDate.now()) < 0) {
    		this.status = Status.COMPLETED;
    	}
    }
    
    public void setStatusCancelled() {
    	this.status = Status.CANCELLED;
    }
    
    public Status getStatus() {
    	return this.status;
    }
    
    @Override
    public boolean equals(Object o) {
    	return equals((Booking) o);
    }
    
    @Override
    public int compareTo(Object o)
    {
    	return compareTo((Booking) o);
    }

    public int compareTo(Booking booking) {
    	if(this.id < booking.getBookingId())
    	{
    		return -1;
    	}
    	else if(this.id > booking.getBookingId())
    	{
    		return 1;
    	}
    	return 0;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", customer=" + customer +
                ", worker=" + worker +
                ", business=" + business +
                ", service=" + service +
                ", bookingSlot=" + bookingSlot +
                '}';
    }
}