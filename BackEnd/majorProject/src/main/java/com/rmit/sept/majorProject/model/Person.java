package com.rmit.sept.majorProject.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@MappedSuperclass
public abstract class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long     id;
    @NotNull
    protected String   name;
    @NotNull
    protected String   username;
    @NotNull
    protected String   password;

    public enum Role{
        ADMIN,
        WORKER,
        CUSTOMER
    }

    protected Role     ROLE_TYPE;   
    
    // --------------GETTERS AND SETTERS---------------
   
    public Long getId(){
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
    public Role getRole(){
        return this.ROLE_TYPE;
    }

}
 