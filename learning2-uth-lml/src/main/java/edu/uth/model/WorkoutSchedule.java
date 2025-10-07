package edu.uth.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class WorkoutSchedule {
    private String scheduleId;
    private String memberId;
    private String trainerId;
    private LocalDate date;
    private LocalTime time;
    private String exercises;
    private String progress; 
    
    public WorkoutSchedule(String scheduleId, String memberId, String trainerId, 
                          LocalDate date, LocalTime time, String exercises) {
        this.scheduleId = scheduleId;
        this.memberId = memberId;
        this.trainerId = trainerId;
        this.date = date;
        this.time = time;
        this.exercises = exercises;
        this.progress = "PENDING";
    }

    // getter & setter
    public String getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(String scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(String trainerId) {
        this.trainerId = trainerId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getExercises() {
        return exercises;
    }

    public void setExercises(String exercises) {
        this.exercises = exercises;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }
    
    
    public void updateProgress(String progress) {
        this.progress = progress;
    }
}