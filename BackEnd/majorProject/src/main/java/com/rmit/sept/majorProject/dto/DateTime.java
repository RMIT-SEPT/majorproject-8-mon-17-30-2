package com.rmit.sept.majorProject.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public interface DateTime {
    
    public LocalDate getDate();
    public LocalTime getStartTime();
    public LocalTime getEndTime();

}
