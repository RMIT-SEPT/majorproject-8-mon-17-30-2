package com.rmit.sept.majorProject.model;

import javax.persistence.*;

public abstract class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long     id;

    protected String   name;
    protected String   username;
    protected String   password;

    public enum Role{
        ADMIN,
        WORKER,
        CUSTOMER
    }

    protected Role     roleType;
    protected Business business;    
    
    // --------------GETTERS AND SETTERS---------------
   
    public Long getID(){
        return this.id;
    }
    public String getName(){
        return this.name;
    }
    public String getUsername(){
        return this.username;
    }
    public boolean setUsername(String newUsername){
        String current = this.username;
        this.username = newUsername;
        return (current != newUsername);
    }
    public String getPassword(){
        return this.password;
    }
    public boolean setPassword(String newPassword){
        String current = this.password;
        this.password = newPassword;
        return (current != newPassword);
    }
    public Business getBusiness(){
        return this.business;
    }
    public boolean setBusiness(Business newBusiness){
        long current = this.business.getID();
        this.business = newBusiness;
        return (current != newBusiness.getID());
    }
    public Role getRole(){
        return this.roleType;
    }

}
 