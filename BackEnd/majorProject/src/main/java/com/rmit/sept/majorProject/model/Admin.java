package com.rmit.sept.majorProject.model;

import javax.persistence.*;

@Entity
public class Admin extends Person{

    @OneToOne
    private Business business;
    
    public Admin(){
        this.role = Role.ADMIN;
    }

    public Admin(String name, String username, String password){
        this.name = name;
        this.username = username;
        this.password = password;
        this.role = Role.ADMIN;
    }

    //copy constructor
    public Admin(Admin other){
        this.name = other.getName();
        this.username = other.getUsername();
        this.password = other.getPassword();
        this.business = other.getBusiness();
        this.role = Role.ADMIN;
    }

    // --------------GETTERS AND SETTERS---------------

    /* business getter/setter re-used in Customer and Worker since Hibernate 
    doesn't really like one-to-many when dealing with inheritance/abstraction */
    public Business getBusiness(){
        return this.business;
    }

    public void setBusiness(Business newBusiness){
        if(this.business != null && this.business.equals(newBusiness)){
            return;
        }
        this.business = newBusiness;
        newBusiness.setBusinessOwner(this);
    }
    
    public String getUsername() {
    	return this.username;
    }
    
    public String getPassword() {
    	return this.password;
    }

    //Equals used to compare if 2 admins are equal, used for testing purposes
    @Override
    public boolean equals(Object obj) {
        if(obj != null && obj instanceof Admin){
            return equals((Admin)obj);
        }

        return false;
    }
    public boolean equals(Admin admin) {
        return ((name == admin.name) && (username == admin.username) && (password == admin.password));
    }


}