package edu.uth.service;

import java.util.ArrayList; 
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.uth.model.Attendance;

public class AttendanceService {
    private final List<Attendance> attendanceRecords;
    
    public AttendanceService() {
        this.attendanceRecords = new ArrayList<>();
    }
    
    public void addAttendance(Attendance attendance) {
        attendanceRecords.add(attendance);
    }
    
    public Map<String, Double> getAttendanceRates() {
        Map<String, Double> rates = new HashMap<>();
        return rates;
    }
    
    public double getMemberAttendanceRate(String memberId) {
        // du lieu mau // chua suy nghi duoc logic
        return 85.5; 
    }
    
}