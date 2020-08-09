package com.rmit.sept.majorProject.model;

import javax.persistence.*;

@Entity
public class Service {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    
    private String title;
    private String description;
    private int    capacity;
    @ManyToOne
    private Business business;

    public Service(String title, String description, int capacity){
        this.title = title;
        this.description = description;
        this.capacity = capacity;
    }

    public Service(){}

    // --------------GETTERS AND SETTERS---------------

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
}