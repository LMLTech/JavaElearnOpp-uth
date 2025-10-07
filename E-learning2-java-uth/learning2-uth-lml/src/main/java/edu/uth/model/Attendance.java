package edu.uth.model;

import java.time.LocalDate;

public class Attendance {
    private String attendanceId;
    private String memberId;
    private LocalDate date;
    private boolean present;
    
    public Attendance(String attendanceId, String memberId, LocalDate date, boolean present) {
        this.attendanceId = attendanceId;
        this.memberId = memberId;
        this.date = date;
        this.present = present;
    }

    public String getAttendanceId() {
        return attendanceId;
    }

    public void setAttendanceId(String attendanceId) {
        this.attendanceId = attendanceId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public boolean isPresent() {
        return present;
    }

    public void setPresent(boolean present) {
        this.present = present;
    }
    
}