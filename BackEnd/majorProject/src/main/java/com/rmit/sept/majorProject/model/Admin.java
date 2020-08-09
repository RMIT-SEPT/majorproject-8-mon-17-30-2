package com.rmit.sept.majorProject.model;

import javax.persistence.*;

@Entity
public class Admin extends Person{
    
    @ManyToOne
    private Business business;
    
    public Admin(String name, String username, String password, 
                 Business business){
        this.name = name;
        this.username = username;
        this.password = password;
        this.business = business;
        this.roleType = Role.ADMIN;
    }

    public Admin(){}

    // --------------GETTERS AND SETTERS---------------

    /* business getter/setter re-used in Customer and Worker since Hibernate 
    doesn't really like one-to-many when dealing with inheritance/abstraction */
    public Business getBusiness(){
        return this.business;
    }
    public boolean setBusiness(Business newBusiness){
        long current = this.business.getId();
        this.business = newBusiness;
        return (current != newBusiness.getId());
    }
    
}