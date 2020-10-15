package com.rmit.sept.majorProject.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
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

    @Size(min = 5, max = 10)
    private String phoneNumber;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer", orphanRemoval = true)
    private List<Booking> bookings = new ArrayList<Booking>();

    public Customer() {
        this.role = Role.CUSTOMER;
    }

    public Customer(String name, String username, String password, String address, String email, String phoneNumber) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.role = Role.CUSTOMER;
    }

    // copy constructor
    public Customer(Customer other) {
        this.name = other.getName();
        this.username = other.getUsername();
        this.password = other.getPassword();
        this.email = other.getEmail();
        this.address = other.getAddress();
        this.phoneNumber = other.getPhoneNumber();
        this.role = Role.CUSTOMER;
    }

    // --------------GETTERS AND SETTERS---------------

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String newEmail) {
        this.email = newEmail;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String newAddress) {
        this.address = newAddress;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String newPhoneNumber) {
        this.phoneNumber = newPhoneNumber;
    }

    public Iterable<Booking> getBookings() {
        return this.bookings;
    }

    public boolean equals(Customer customer) {
        if (customer == null) {
            return false;
        }
        if (customer.getName() == this.name && 
            customer.getEmail() == this.email &&
            customer.getUsername() == this.username &&
            customer.getPassword() == this.password &&
            customer.getAddress() == this.address &&
            customer.getPhoneNumber() == this.phoneNumber) {
                return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object object) {
        return equals((Customer) object);
    }

    @Override
    public String toString() {
        return "Customer{" + "id=" + id + ", username='" + username + '\'' + '}';
    }
}