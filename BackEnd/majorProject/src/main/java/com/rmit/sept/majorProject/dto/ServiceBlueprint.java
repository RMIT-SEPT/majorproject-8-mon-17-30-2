package com.rmit.sept.majorProject.dto;

public class ServiceBlueprint {

    private String title;
    private String description;
    private int capacity;

    public ServiceBlueprint() {
    }

    public ServiceBlueprint(String title, String description, int capacity) {
        this.title = title;
        this.description = description;
        this.capacity = capacity;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

}
