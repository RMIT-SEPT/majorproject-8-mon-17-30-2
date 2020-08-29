package com.rmit.sept.majorProject.dto;

import com.rmit.sept.majorProject.model.Business;

public class BusinessSummary {
    private Long   id;
    private String name;


    public BusinessSummary(Business business) {
        this.id = business.getId();
        this.name = business.getBusinessName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
