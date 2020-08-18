package com.rmit.sept.majorProject.model;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import javax.persistence.*;

@MappedSuperclass
public abstract class Slot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    protected LocalDate date;
    protected LocalTime startTime;
    protected LocalTime endTime;

    // --------------GETTERS AND SETTERS---------------
    
    public LocalDate getDate(){
        return this.date;
    }
    public boolean setDate(LocalDate newDate){
        LocalDate current = this.date;
        this.date = newDate;
        return (current != newDate);
    }
    public LocalTime getStartTime(){
        return this.startTime;
    }
    public boolean setStartTime(LocalTime newStart){
        LocalTime current = this.startTime;
        this.startTime = newStart;
        return (current != newStart);
    }
    public LocalTime getEndTime(){
        return this.startTime;
    }
    public boolean setEndTime(LocalTime newEnd){
        LocalTime current = this.endTime;
        this.endTime = newEnd;
        return (current != newEnd);
    }
    public Long getDuration(){
        return ChronoUnit.MINUTES.between(this.startTime, this.endTime);
    }

}