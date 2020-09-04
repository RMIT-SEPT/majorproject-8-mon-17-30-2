package com.rmit.sept.majorProject.model;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Service {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long   id;  

    private String title;
    private String description;
    private int    capacity;
    
    @ManyToOne
    private Business business;

    @ManyToMany(mappedBy = "services")
    private List<Worker> workers;

    public Service(String title, String description, int capacity){
        this.title = title;
        this.description = description;
        this.capacity = capacity;
    }

    public Service(){}

    // --------------GETTERS AND SETTERS---------------


    public Long getId(){
        return this.id;
    }
    public String getTitle(){
        return this.title;
    }
    public boolean setTitle(String newTitle){
        String current = this.title;
        this.title = newTitle;
        return (current != newTitle);
    }
    public String getDescription(){
        return this.description;
    }
    public boolean setDescription(String newDescription){
        String current = this.description;
        this.description = newDescription;
        return (current != newDescription);
    }
    public int getCapacity(){
        return this.capacity;
    }
    public boolean setCapacity(int newCapacity){
        int current = this.capacity;
        this.capacity = newCapacity;
        return (current != newCapacity);
    }

    public void setBusiness(Business newBusiness) {
        if(sameAsFormer(newBusiness)){
            return;
        }
        Business oldBusiness = this.business;
        this.business = newBusiness;
        if (oldBusiness!=null){
            oldBusiness.removeService(this);
        }
        if(newBusiness!=null){
            newBusiness.addService(this);
        }
    }

    private boolean sameAsFormer(Business newBusiness) {
        return this.business==null ? newBusiness == null : this.business.equals(newBusiness);
    }
}