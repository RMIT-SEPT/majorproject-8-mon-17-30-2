package com.rmit.sept.majorProject.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class BookingSlot extends Slot {

    @ManyToMany
    private List<Service> availableServices = new ArrayList<Service>();

    @ManyToOne
    private Service bookedService;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bookingSlot", orphanRemoval = true)
    private List<Booking> bookings = new ArrayList<Booking>();

    @ManyToOne
    private WorkSlot workSlot;

    private boolean isSet;

    public BookingSlot(LocalDate date, LocalTime startTime, 
                       LocalTime endTime, List<Service> services) {
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.availableServices = services;
    }

    public BookingSlot() {
    }

    // --------------GETTERS AND SETTERS---------------

    public Iterable<Service> getAvailableServices() {
        return this.availableServices;
    }

    public boolean searchServiceExist(String title) {
        for (Service service : getAvailableServices()) {
            if (service.getTitle().equals(title)) {
                return true;
            }
        }
        return false;
    }

    public Service findService(String title) {
        for (Service service : getAvailableServices()) {
            if (service.getTitle().equals(title)) {
                return service;
            }
        }
        return null;
    }

    public void addAvailableService(Service newService) {
        if (!availableServices.contains(newService)) {
            this.availableServices.add(newService);
        }
    }

    public void removeAvailableService(Service service) {
        availableServices.remove(service);
    }

    public Service getBookedService() {
        return this.bookedService;
    }

    public void addBooking(Booking booking) {
        this.bookings.add(booking);
        if (this.bookedService == null) {
            setBookedService(booking.getService());
        }
        this.getWorkSlot().getWorker().getBusiness().addBooking(booking);
    }

    public void setBookedService(Service service) {
        this.bookedService = service;
        this.isSet = true;
    }

    public void removeBookedService() {
        this.bookedService = null;
        this.isSet = false;
    }

    public List<Booking> getBookings() {
        return this.bookings;
    }

    public WorkSlot getWorkSlot() {
        return this.workSlot;
    }

    public boolean fullyBooked() {
        if (!isSet) {
            return false;
        }
        return (this.bookings.size() >= bookedService.getCapacity());
    }

    public java.time.LocalDate getBookSlotDate() {
        return this.date;
    }

    public void setWorkSlot(WorkSlot newSlot) {
        // prevent endless loop
        if (sameAsFormer(newSlot)) {
            return;
        }
        // set new owner
        WorkSlot oldSlot = this.workSlot;
        this.workSlot = newSlot;
        // remove from the old workslot
        if (oldSlot != null) {
            oldSlot.removeBookingSlot(this);
        }
        // set myself into new owner
        if (workSlot != null) {
            workSlot.addBookingSlot(this);
        }
    }

    private boolean sameAsFormer(WorkSlot newSlot) {
        return workSlot == null ? newSlot == null : workSlot.equals(newSlot);
    }

    public boolean isSet() {
        return this.isSet;
    }

    public void setAvailableServices(List<Service> availableServices) {
        this.availableServices = availableServices;
    }

    @Override
    public String toString() {
        return "BookingSlot{" + "date=" + date + ", startTime=" + startTime + ", endTime=" + endTime + '}';
    }

    public void removeBooking(Booking booking) {
        bookings.remove(booking);
        if (bookings.size() == 0) {
            removeBookedService();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (((BookingSlot) o).getBookSlotDate().compareTo(date) == 0
                && ((BookingSlot) o).getStartTime().compareTo(startTime) == 0
                && ((BookingSlot) o).getEndTime().compareTo(endTime) == 0) {
            return true;
        }
        return false;
    }

}