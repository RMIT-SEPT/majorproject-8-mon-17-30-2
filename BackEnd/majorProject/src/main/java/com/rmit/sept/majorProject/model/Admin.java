package com.rmit.sept.majorProject.model;

import javax.persistence.*;

@Entity
public class Admin extends Person{

    @OneToOne(cascade = CascadeType.ALL)
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
    public boolean setBusiness(Business newBusiness){
//        long current = this.business.getId();
        this.business = newBusiness;
//        return (current != newBusiness.getId());
        return true;
    }
    
    public boolean setNewBusiness(Business newBusiness)
    {
      long current = this.business.getId();
      this.business = newBusiness;
      return (current != newBusiness.getId());
    }
    
    public String getUsername() {
    	return this.username;
    }
    
    public String getPassword() {
    	return this.password;
    }
    
}