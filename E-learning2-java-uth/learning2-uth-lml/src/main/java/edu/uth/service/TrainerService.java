package edu.uth.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import edu.uth.model.Attendance;
import edu.uth.model.Member;
import edu.uth.model.Trainer;
import edu.uth.model.User;
import edu.uth.model.WorkoutSchedule;

public class TrainerService {
    private List<User> users;
    private MemberService memberService;
    private ScheduleService scheduleService;
    private AttendanceService attendanceService;
    private Scanner scanner;

    public TrainerService(List<User> users) {
        this.users = users;
    }

    // Constructor đầy đủ tham số
    public TrainerService(List<User> users, MemberService memberService,
                         ScheduleService scheduleService, AttendanceService attendanceService, Scanner scanner) {
        this.users = users;
        this.memberService = memberService;
        this.scheduleService = scheduleService;
        this.attendanceService = attendanceService;
        this.scanner = scanner;
    }

    public TrainerService() {
    }

    public void showTrainerDashboard() {
        while (true) {
            System.out.println("\n=== MENU HUAN LUYEN VIEN ===");
            System.out.println("1. Giao lich tap cho thanh vien");
            System.out.println("2. Diem danh buoi tap");
            System.out.println("3. Xem tien do tap luyen");
            System.out.println("4. Danh sach lich tap cua toi");
            System.out.println("5. Quay lai (Dang xuat)");
            System.out.print("Lua chon cua ban: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    assignWorkoutSchedule();
                    break;
                case 2:
                    trackMemberAttendance();
                    break;
                case 3:
                    trackMemberProgress();
                    break;
                case 4:
                    viewSchedules();
                    break;
                case 5:
                    return;
                default:
                    System.out.println(" Vui long chon dung so trong menu!");
            }
        }
    }

    private void assignWorkoutSchedule() {
        System.out.println("\n--- GIAO LICH TAP ---");
        List<Member> members = memberService.getAllMembers();
        for (Member member : members) {
            System.out.println(member.getUserId() + " - " + member.getName());
        }
        
        System.out.print("Nhap ID hoi vien: ");
        String memberId = scanner.nextLine();
        System.out.print("Nhap ngay tap (yyyy-mm-dd): ");
        String dateStr = scanner.nextLine();
        System.out.print("Nhap gio tap (hh:mm): ");
        String timeStr = scanner.nextLine();
        System.out.print("Nhap noi dung bai tap: ");
        String exercises = scanner.nextLine();
        
        LocalDate date = LocalDate.parse(dateStr);
        LocalTime time = LocalTime.parse(timeStr);
        String scheduleId = "SCH" + System.currentTimeMillis();
        
        WorkoutSchedule schedule = new WorkoutSchedule(scheduleId, memberId,
            "T001", date, time, exercises); // ID trainer mẫu
        scheduleService.addSchedule(schedule);
        
        System.out.println(" Da tao lich tap moi cho hoi vien " + memberId);
    }

    private void trackMemberAttendance() {
        System.out.println("\n--- DIEM DANH TAP LUYEN ---");
        List<Member> members = memberService.getAllMembers();
        for (Member member : members) {
            System.out.println(member.getUserId() + " - " + member.getName());
        }
        
        System.out.print("Nhap ID hoi vien: ");
        String memberId = scanner.nextLine();
        System.out.print("Hoi vien co mat? (y/n): ");
        String present = scanner.nextLine();
        
        boolean isPresent = present.equalsIgnoreCase("y");
        String attendanceId = "ATT" + System.currentTimeMillis();
        
        Attendance attendance = new Attendance(attendanceId, memberId, LocalDate.now(), isPresent);
        attendanceService.addAttendance(attendance);
        
        if (isPresent) {
            System.out.println(" Hoi vien " + memberId + " da diem danh thanh cong.");
        } else {
            System.out.println("Hoi vien " + memberId + " vang mat buoi tap nay.");
        }
    }

    private void trackMemberProgress() {
        System.out.println("\n--- BAO CAO TIEN DO TAP ---");
        List<WorkoutSchedule> schedules = scheduleService.getAllSchedules();
        
        System.out.printf("%-12s %-15s %-12s %-20s %-10s\n", 
            "Lich ID", "Hoi Vien", "Ngay", "Bai Tap", "Tien do");
        for (WorkoutSchedule schedule : schedules) {
            Member member = memberService.findMemberById(schedule.getMemberId());
            System.out.printf("%-12s %-15s %-12s %-20s %-10s\n",
                schedule.getScheduleId(), member.getName(),
                schedule.getDate(), schedule.getExercises(), schedule.getProgress());
        }
    }

    private void viewSchedules() {
        System.out.println("\n--- DANH SACH LICH TAP CUA HUAN LUYEN VIEN ---");
        List<WorkoutSchedule> mySchedules = scheduleService.getSchedulesByTrainer("T001"); // Giả định trainer ID
        
        if (mySchedules.isEmpty()) {
            System.out.println(" Hien tai ban chua co lich tap nao.");
            return;
        }
        
        for (WorkoutSchedule schedule : mySchedules) {
            Member member = memberService.findMemberById(schedule.getMemberId());
            System.out.println("Hoi vien: " + member.getName() +
                             " | Ngay: " + schedule.getDate() +
                             " | Gio: " + schedule.getTime() +
                             " | Bai tap: " + schedule.getExercises());
        }
    }

    public List<Trainer> getAllTrainers() {
        List<Trainer> trainers = new ArrayList<>();
        for (User user : users) {
            if (user instanceof Trainer) {
                trainers.add((Trainer) user);
            }
        }
        return trainers;
    }
    
    public Trainer findTrainerById(String trainerId) {
        for (User user : users) {
            if (user instanceof Trainer && user.getUserId().equals(trainerId)) {
                return (Trainer) user;
            }
        }
        return null;
    }
}
