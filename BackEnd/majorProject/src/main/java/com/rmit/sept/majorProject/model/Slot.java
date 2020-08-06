package com.rmit.sept.majorProject.model;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import javax.persistence.*;

@Entity
public abstract class Slot {

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
    public LocalTime getEnDTime(){
        return this.startTime;
    }
    public boolean setEnDTime(LocalTime newEnd){
        LocalTime current = this.endTime;
        this.endTime = newEnd;
        return (current != newEnd);
    }
    public Long getDuration(){
        return ChronoUnit.MINUTES.between(this.startTime, this.endTime);
    }

}