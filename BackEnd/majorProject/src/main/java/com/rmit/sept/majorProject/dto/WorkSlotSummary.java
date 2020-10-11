package com.rmit.sept.majorProject.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import com.rmit.sept.majorProject.model.WorkSlot;
import com.rmit.sept.majorProject.model.BookingSlot;

public class WorkSlotSummary implements DateTime{

    private Long id;
    private Long workerId;
    private String workerName;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private ArrayList<BookingSlotSummary> bookingSlots = new ArrayList<BookingSlotSummary>();

    public WorkSlotSummary(WorkSlot workSlot){
        this.id = workSlot.getId();
        this.workerId = workSlot.getWorker().getId();
        this.workerName = workSlot.getWorker().getName();
        this.date = workSlot.getDate();
        this.startTime = workSlot.getStartTime();
        this.endTime = workSlot.getEndTime();
        for(BookingSlot bookingSlot : workSlot.getBookingSlots()){
            this.bookingSlots.add(new BookingSlotSummary(bookingSlot));
        }
    }

    public Long getId() {
        return id;
    }

    public Long getWorkerId() {
        return workerId;
    }

    public String getWorkerName() {
        return workerName;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public ArrayList<BookingSlotSummary> getBookingSlots() {
        return bookingSlots;
    }   

    @Override
    public boolean equals(Object o) {
    	if(((WorkSlotSummary) o).getDate().isEqual(date)
    			&& ((WorkSlotSummary) o).getStartTime().compareTo(startTime) == 0
    			&& ((WorkSlotSummary) o).getEndTime().compareTo(endTime) == 0
    			&& ((WorkSlotSummary) o).getWorkerId() == workerId) {
    		return true;
    	}
    	return false;
    }
    
}