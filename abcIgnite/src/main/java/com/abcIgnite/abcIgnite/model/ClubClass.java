package com.abcIgnite.abcIgnite.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.time.LocalTime;

@Table(name = "CLUB_CLASS_")
@Entity
public class ClubClass {

    @jakarta.persistence.Id
    @Id
    @Column(name="UID_")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long clubClassId;

    @Column(name = "NAME_")
    private String name;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "START_DATE_")
    private LocalDate startDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "END_DATE_")
    private LocalDate endDate;

    @Column(name = "START_TIME_")
    private LocalTime startTime;

    @Column(name = "DURATION_")
    private Integer duration;

    @Column(name = "CAPACITY_")
    private Integer capacity;

    public void setId(Long clubClassId) {
        this.clubClassId = clubClassId;
    }

    public Long getId() {
        return clubClassId;
    }

    public void setName(String name){
        this.name=name;
    }

    public String getName(){
        return name;
    }

    public void setStartDate(LocalDate startDate){
        this.startDate=startDate;
    }

    public LocalDate getStartDate(){
        return startDate;
    }

    public void setEndDate(LocalDate endDate){
        this.endDate=endDate;
    }

    public LocalDate getEndDate(){
        return endDate;
    }

    public void setStartTime(LocalTime startTime){
        this.startTime=startTime;
    }

    public LocalTime getStartTime(){
        return startTime;
    }

    public void setDuration(Integer duration){
        this.duration=duration;
    }

    public Integer getDuration(){
        return duration;
    }

    public void setCapacity(Integer capacity){
        this.capacity=capacity;
    }

    public Integer getCapacity(){
        return capacity;
    }
}
