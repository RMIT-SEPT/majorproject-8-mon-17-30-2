package com.rmit.sept.majorProject.dto;

import java.util.ArrayList;

import com.rmit.sept.majorProject.model.Business;
import com.rmit.sept.majorProject.model.Service;
import com.rmit.sept.majorProject.model.Worker;

public class BusinessSummary {

    private Long id;
    private String name;
    private ArrayList<ServiceSummary> services = new ArrayList<ServiceSummary>();
    private ArrayList<WorkerSummary> workers = new ArrayList<WorkerSummary>();

    public BusinessSummary(Business business) {
        this.id = business.getId();
        this.name = business.getBusinessName();
        for (Service service : business.getServices()) {
            this.services.add(new ServiceSummary(service));
        }
        for (Worker worker : business.getWorkers()) {
            this.workers.add(new WorkerSummary(worker));
        }
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Iterable<ServiceSummary> getServices() {
        return services;
    }

    public Iterable<WorkerSummary> getWorkers() {
        return workers;
    }

}
