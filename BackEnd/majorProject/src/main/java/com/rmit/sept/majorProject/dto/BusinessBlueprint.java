package com.rmit.sept.majorProject.dto;

public class BusinessBlueprint {

    private String name;
    private Iterable<ServiceBlueprint> services;

    public BusinessBlueprint(){        
    }

    public BusinessBlueprint(String name, Iterable<ServiceBlueprint> services) {
        this.name = name;
        this.services = services;
    }

    public String getBusinessName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Iterable<ServiceBlueprint> getServices() {
        return services;
    }

    public void setServices(Iterable<ServiceBlueprint> services) {
        this.services = services;
    }
    
}