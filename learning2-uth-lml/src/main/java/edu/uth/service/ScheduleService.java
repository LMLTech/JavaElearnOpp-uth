package edu.uth.service;

import java.util.ArrayList;
import java.util.List;

import edu.uth.model.WorkoutSchedule;

public class ScheduleService {
    private List<WorkoutSchedule> schedules;
    
    public ScheduleService() {
        this.schedules = new ArrayList<>();
    }
    
    public void addSchedule(WorkoutSchedule schedule) {
        schedules.add(schedule);
    }
    
    public List<WorkoutSchedule> getAllSchedules() {
        return schedules;
    }
    
    public List<WorkoutSchedule> getSchedulesByTrainer(String trainerId) {
        List<WorkoutSchedule> result = new ArrayList<>();
        for (WorkoutSchedule schedule : schedules) {
            if (schedule.getTrainerId().equals(trainerId)) {
                result.add(schedule);
            }
        }
        return result;
    }
    
    public List<WorkoutSchedule> getSchedulesByMember(String memberId) {
        List<WorkoutSchedule> result = new ArrayList<>();
        for (WorkoutSchedule schedule : schedules) {
            if (schedule.getMemberId().equals(memberId)) {
                result.add(schedule);
            }
        }
        return result;
    }
    
    public void saveAllSchedules() {
        // Logic lưu schedules vào file
    }
}
