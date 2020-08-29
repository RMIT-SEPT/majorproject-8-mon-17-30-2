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

    public String getName() {
        return name;
    }

}
