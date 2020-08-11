package com.rmit.sept.majorProject.model;

import javax.persistence.*;

import org.springframework.lang.NonNull;


@Entity
public class Admin extends Person{
    
	
	//Username and password somehow prevents the system from creating the customer
//	private String username;
//	private String password;
    @ManyToOne
    private Business business;
    private final Role ROLE_TYPE = Role.ADMIN;
    
    public Admin(String name, String username, String password){
        this.name = name;
        this.username = username;
        this.password = password;
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
    
    public Role getRole() {
    	return this.ROLE_TYPE;
    }
    
    public String getUsername() {
    	return this.username;
    }
    
//    public boolean setUsername(String username) {
//    	String current = this.username;
//        this.username = username;
//        return (current != username);
//    }
    
    public String getPassword() {
    	return this.password;
    }
    
//    public boolean setPassword(String password) {
//    	String current = this.password;
//        this.password = password;
//        return (current != password);
//    }
    
}