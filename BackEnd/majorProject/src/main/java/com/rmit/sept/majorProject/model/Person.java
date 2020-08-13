package com.rmit.sept.majorProject.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@MappedSuperclass
public abstract class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long     id;
    @NotNull
    @NotEmpty
    protected String   name;
    @NotNull
    @NotEmpty
    protected String   username;
    @NotNull
    @NotEmpty
    protected String   password;

    public enum Role{
        ADMIN,
        WORKER,
        CUSTOMER
    }

    protected Role     role;
    
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
    public void setUsername(String newUsername){
        this.username = newUsername;
    }
    public String getPassword(){
        return this.password;
    }
    public void setPassword(String newPassword){
        this.password = newPassword;
    }
    public Role getRole(){
        return this.role;
    }

}
 