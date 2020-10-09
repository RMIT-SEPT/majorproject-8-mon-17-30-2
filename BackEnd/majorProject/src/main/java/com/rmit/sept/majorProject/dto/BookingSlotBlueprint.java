package com.rmit.sept.majorProject.dto;

public class BookingSlotBlueprint {

    private Iterable<Long> serviceIds;
    private String date;
    private String startTime;
    private String endTime;
    private Long workSlotId;

    public BookingSlotBlueprint(){  
        System.out.println("aaa");      
    }

    public BookingSlotBlueprint(Long workSlotId, String date, String startTime,
                                String endTime, Iterable<Long> serviceIds) {
        this.serviceIds = serviceIds;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.workSlotId = workSlotId;
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

    public Iterable<Long> getServiceIds() {
        return serviceIds;
    }

    public void setServiceIds(Iterable<Long> serviceIds) {
        this.serviceIds = serviceIds;
    }

    public Long getWorkSlotId() {
        return workSlotId;
    }

    public void setWorkSlotId(Long workSlotId) {
        this.workSlotId = workSlotId;
    }
    
    @Override
    public boolean equals(Object o) {
    	if(((BookingSlotBlueprint)o).getDate().equals(date)
    			&& ((BookingSlotBlueprint)o).getStartTime().equals(startTime)
    			&& ((BookingSlotBlueprint)o).getEndTime().equals(endTime)
    			&& ((BookingSlotBlueprint)o).getWorkSlotId() == workSlotId) {
    		return true;
    	}
    	return false;
    }
    
}