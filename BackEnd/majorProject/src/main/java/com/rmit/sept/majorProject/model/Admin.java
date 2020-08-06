package com.rmit.sept.majorProject.model;

import javax.persistence.*;

@Entity
public class Admin extends Person{
    
    public Admin(String name, String username, String password, 
                 Business business){
        this.name = name;
        this.username = username;
        this.password = password;
        this.business = business;
        this.roleType = Role.ADMIN;
    }
    
}