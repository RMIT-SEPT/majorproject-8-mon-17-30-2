package com.rmit.sept.majorProject.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class WorkSlotBlueprint {

	private Long workerId;
	
	//Format for the date string is yyyy-mmm-dd
	private LocalDate date;
	//Format for the time string is hh:mm or hh:mm:ss
	private LocalTime startTime;
	private LocalTime endTime;
	
	public WorkSlotBlueprint(Long workerId, String date, String startTime, String endTime) {
		this.workerId = workerId;
		this.date = LocalDate.parse(date);
		this.startTime = LocalTime.parse(startTime);
		this.endTime = LocalTime.parse(endTime);
	}
	
	public Long getWorkerId() {
		return this.workerId;
	}
	
	public LocalDate getDate() {
		return this.date;
	}
	
	public LocalTime getStartTime() {
		return this.startTime;
	}
	
	public LocalTime getEndTime() {
		return this.endTime;
	}
}
