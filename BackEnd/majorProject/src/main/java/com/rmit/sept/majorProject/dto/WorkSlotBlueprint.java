package com.rmit.sept.majorProject.dto;

public class WorkSlotBlueprint {

    private Long workerId;
    private Long businessId;
    private String date;
    private String startTime;
    private String endTime;

    public WorkSlotBlueprint(){        
    }

    public WorkSlotBlueprint(Long workerId, Long businessId,
              String date, String startTime, String endTime) {
        this.workerId = workerId;
        this.businessId = businessId;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Long getWorkerId() {
        return workerId;
    }

    public void setWorkerId(Long workerId) {
        this.workerId = workerId;
    }

    public Long getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Long businessId) {
        this.businessId = businessId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
    
}