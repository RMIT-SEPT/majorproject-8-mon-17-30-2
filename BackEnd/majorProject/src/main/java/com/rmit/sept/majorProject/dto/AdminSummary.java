package com.rmit.sept.majorProject.dto;

import com.rmit.sept.majorProject.model.Admin;

public class AdminSummary {

    private long id;
    private String name;
    private String username;
    private String businessname;
    private long businessId;

    public AdminSummary(){
    }

    public AdminSummary(Admin admin){
        this.id = admin.getId();
        this.name = admin.getName();
        this.username = admin.getUsername();
        this.businessname = admin.getBusiness().getBusinessName();
        this.businessId = admin.getBusiness().getId();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBusinessname() {
        return businessname;
    }

    public void setBusinessname(String businessname) {
        this.businessname = businessname;
    }

    public long getBusinessId() {
        return businessId;
    }

    public void setBusinessId(long businessId) {
        this.businessId = businessId;
    }

}