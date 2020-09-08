package com.rmit.sept.majorProject.dto;

import com.rmit.sept.majorProject.model.Service;

public class ServiceSummary {

    private Long id;
    private String title;
    
    public ServiceSummary(Service service){
        this.id = service.getId();
        this.title = service.getTitle();
    }

    public Long getId() {
        return id;
    }


    public String getTitle() {
        return title;
    }
   
}