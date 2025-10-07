package edu.uth.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import edu.uth.model.Member;
import edu.uth.model.Trainer;
import edu.uth.model.User;
import edu.uth.model.WorkoutSchedule;

public class MemberService {
    private final List<User> users;
    private TrainerService trainerService;
    private ScheduleService scheduleService;
    private AttendanceService attendanceService;
    private Scanner scanner;

    public MemberService(List<User> users) {
        this.users = users;
    }

    // Constructor mới cho đầy đủ dependency
    public MemberService(List<User> users, TrainerService trainerService, 
                        ScheduleService scheduleService, AttendanceService attendanceService, Scanner scanner) {
        this.users = users;
        this.trainerService = trainerService;
        this.scheduleService = scheduleService;
        this.attendanceService = attendanceService;
        this.scanner = scanner;
    }

    public void showMemberDashboard(Member currentMember) {
        while (true) {
            System.out.println("=== KHU VUC DANG NHAP THANH VIEN ===");
            System.out.println("1. Xem kế hoạch tập luyện");
            System.out.println("2. Cập nhật tiến độ");
            System.out.println("3. Gia hạn gói tập");
            System.out.println("4. Xem trạng thái hội viên");
            System.out.println("5. Đăng xuất");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    viewWorkoutSchedules(currentMember);
                    break;
                case 2:
                    updateProgress(currentMember);
                    break;
                case 3:
                    renewMembership(currentMember);
                    break;
                case 4:
                    viewMembershipStatus(currentMember);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("⚠️ Lựa chọn không hợp lệ, vui lòng thử lại!");
            }
        }
    }

    private void viewWorkoutSchedules(Member currentMember) {
        System.out.println("\n=== DANH SÁCH LỊCH TẬP CỦA BẠN ===");
        List<WorkoutSchedule> mySchedules = scheduleService.getSchedulesByMember(currentMember.getUserId());
        
        if (mySchedules.isEmpty()) {
            System.out.println("📌 Hiện tại bạn chưa có lịch tập nào được phân công.");
            return;
        }
        
        for (WorkoutSchedule schedule : mySchedules) {
            Trainer trainer = trainerService.findTrainerById(schedule.getTrainerId());
            System.out.println("Huấn luyện viên: " + trainer.getName() + 
                             " | Ngày: " + schedule.getDate() + 
                             " | Giờ: " + schedule.getTime() +
                             " | Bài tập: " + schedule.getExercises() +
                             " | Tiến độ: " + schedule.getProgress());
        }
    }

    private void updateProgress(Member currentMember) {
        System.out.println("\n=== CẬP NHẬT TIẾN ĐỘ LUYỆN TẬP ===");
        List<WorkoutSchedule> mySchedules = scheduleService.getSchedulesByMember(currentMember.getUserId());
        
        if (mySchedules.isEmpty()) {
            System.out.println("❌ Bạn chưa có lịch tập để cập nhật.");
            return;
        }
        
        for (int i = 0; i < mySchedules.size(); i++) {
            WorkoutSchedule schedule = mySchedules.get(i);
            System.out.println((i + 1) + ". " + schedule.getExercises() + " - Tiến độ: " + schedule.getProgress());
        }
        
        System.out.print("👉 Chọn lịch tập (theo số): ");
        int scheduleIndex = scanner.nextInt() - 1;
        scanner.nextLine();
        
        if (scheduleIndex >= 0 && scheduleIndex < mySchedules.size()) {
            System.out.print("Nhập tiến độ mới (PENDING/IN_PROGRESS/COMPLETED): ");
            String newProgress = scanner.nextLine();
            
            WorkoutSchedule selectedSchedule = mySchedules.get(scheduleIndex);
            selectedSchedule.updateProgress(newProgress);
            System.out.println("✅ Cập nhật tiến độ thành công!");
        } else {
            System.out.println("⚠️ Lựa chọn không hợp lệ!");
        }
    }

    private void renewMembership(Member currentMember) {
        System.out.println("\n=== GIA HẠN GÓI HỘI VIÊN ===");
        System.out.println(" Gói hiện tại: " + currentMember.getMembershipType());
        System.out.println(" Ngày hết hạn: " + currentMember.getExpiryDate());

        System.out.print("Nhập số tháng bạn muốn gia hạn: ");
        int months = scanner.nextInt();
        scanner.nextLine();
        
        currentMember.renewMembership(months);
        System.out.println("Gia hạn thành công! Ngày hết hạn mới: " + currentMember.getExpiryDate());
    }

    private void viewMembershipStatus(Member member) {
        System.out.println("\n=== THÔNG TIN HỘI VIÊN ===");
        System.out.println(" Tên: " + member.getName());
        System.out.println(" Email: " + member.getEmail());
        System.out.println(" Gói tập: " + member.getMembershipType());
        System.out.println(" Ngày hết hạn: " + member.getExpiryDate());
        System.out.println(" Trạng thái: " + member.getStatus());
        
        double attendanceRate = attendanceService.getMemberAttendanceRate(member.getUserId());
        System.out.printf("Tỷ lệ điểm danh: %.1f%%\n", attendanceRate);
    }

    public List<Member> getAllMembers() {
        List<Member> members = new ArrayList<>();
        for (User user : users) {
            if (user instanceof Member member) {
                members.add(member);
            }
        }
        return members;
    }
    
    public Member findMemberById(String memberId) {
        for (User user : users) {
            if (user instanceof Member && user.getUserId().equals(memberId)) {
                return (Member) user;
            }
        }
        return null;
    }
    
    public void addMember(String name, String email, String membershipType) {
        String newId = "M" + (getAllMembers().size() + 1);
        Member newMember = new Member(newId, name, email, "password123", 
                                    LocalDate.now(), membershipType, 
                                    LocalDate.now().plusMonths(1));
        users.add(newMember);
    }
    public boolean deleteMember(String memberId) {
        return true;
    }
    public double calculateTotalRevenue() {
        return 15000000;
    }
    
    public int getActiveMembersCount() {
        int count = 0;
        for (User user : users) {
            if (user instanceof Member && ((Member) user).getStatus().equals("ACTIVE")) {
                count++;
            }
        }
        return count;
    }
    
    public int getExpiredMembersCount() {
        int count = 0;
        for (User user : users) {
            if (user instanceof Member && ((Member) user).getStatus().equals("EXPIRED")) {
                count++;
            }
        }
        return count;
    }
}
