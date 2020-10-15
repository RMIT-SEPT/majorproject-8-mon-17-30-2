package com.rmit.sept.majorProject.dto;

// Used to receive front-end data in a clean package when creating a WorkSlot object
public class WorkSlotBlueprint {

    private Long workerId;
    private Long businessId;
    private String date;
    private String startTime;
    private String endTime;

    public WorkSlotBlueprint() {
    }

    public WorkSlotBlueprint(Long workerId, Long businessId, String date, String startTime, String endTime) {
        this.workerId = workerId;
        this.businessId = businessId;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Long getWorkerId() {
        return workerId;
    }

    public Long getBusinessId() {
        return businessId;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getDate() {
        return date;
    }

}
