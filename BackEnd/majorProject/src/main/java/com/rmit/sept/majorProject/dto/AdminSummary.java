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

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getBusinessname() {
        return businessname;
    }

    public long getBusinessId() {
        return businessId;
    }

    //Equals used to compare if 2 AdminSummary are equal, used for testing purposes
    @Override
    public boolean equals(Object obj) {
        if(obj != null && obj instanceof AdminSummary){
            return equals((AdminSummary)obj);
        }

        return false;
    }
    public boolean equals(AdminSummary summary) {
        return ((name == summary.name)
                && (username == summary.username)
                && (businessname == summary.businessname)
                && (businessId == summary.businessId));
    }


}