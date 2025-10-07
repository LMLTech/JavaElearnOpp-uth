package edu.uth;

import java.util.Scanner;

import edu.uth.model.Member;
import edu.uth.model.User;
import edu.uth.service.AdminService;
import edu.uth.service.AttendanceService;
import edu.uth.service.AuthService;
import edu.uth.service.MemberService;
import edu.uth.service.ScheduleService;
import edu.uth.service.TrainerService;

public class GymManagementSystem {
    private final AuthService authService;
    private final AdminService adminService;
    private TrainerService trainerService;
    private MemberService memberService;
    private final ScheduleService scheduleService;
    private final AttendanceService attendanceService;
    private final Scanner scanner;
    private User currentUser;

    public GymManagementSystem() {
        this.scanner = new Scanner(System.in);
        this.authService = new AuthService();
        this.memberService = new MemberService(authService.getUsers());
        this.trainerService = new TrainerService(authService.getUsers());
        this.scheduleService = new ScheduleService();
        this.attendanceService = new AttendanceService();
        
        // Khởi tạo các service với đầy đủ dependency
        this.adminService = new AdminService(memberService, trainerService, attendanceService, scanner);
        this.trainerService = new TrainerService(authService.getUsers(), memberService, scheduleService, attendanceService, scanner);
        this.memberService = new MemberService(authService.getUsers(), trainerService, scheduleService, attendanceService, scanner);
    }

    public void start() {
        System.out.println("=== CHUONG TRINH QUAN LY HOI VIEN GYM ===");
        
        while (true) {
            if (currentUser == null) {
                showLoginMenu();
            } else {
                showUserDashboard();
            }
        }
    }

    private void showLoginMenu() {
        System.out.println("\n=== MAN HINH DANG NHAP ===");
        System.out.print("Nhap email: ");
        String email = scanner.nextLine();
        System.out.print("Nhap mat khau: ");
        String password = scanner.nextLine();
        
        currentUser = authService.login(email, password);
        if (currentUser == null) {
            System.out.println("Dang nhap khong thanh cong. Vui long thu lai!");
        } else {
            System.out.println(" Xin chao " + currentUser.getName() + "! Ban da dang nhap thanh cong.");
        }
    }

    private void showUserDashboard() {
        String role = currentUser.getRole();
        switch (role) {
            case "ADMIN":
                System.out.println("\n>>> Ban dang su dung voi quyen ADMIN <<<");
                adminService.showAdminDashboard();
                break;
            case "TRAINER":
                System.out.println("\n>>> Ban dang su dung voi quyen HUAN LUYEN VIEN <<<");
                trainerService.showTrainerDashboard();
                break;
            case "MEMBER":
                System.out.println("\n>>> Ban dang su dung voi quyen HOI VIEN <<<");
                memberService.showMemberDashboard((Member) currentUser);
                break;
            default:
                System.out.println("Khong nhan dien duoc vai tro nguoi dung.");
        }
        
        currentUser = null;
    }

    public static void main(String[] args) {
        GymManagementSystem system = new GymManagementSystem();
        system.start();
    }
}
