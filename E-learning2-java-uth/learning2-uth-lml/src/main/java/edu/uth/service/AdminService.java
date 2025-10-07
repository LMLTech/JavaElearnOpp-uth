package edu.uth.service;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import edu.uth.model.Member;
import edu.uth.model.Trainer;

public class AdminService {
    private final MemberService memberService;
    private final TrainerService trainerService;
    private final AttendanceService attendanceService;
    private final Scanner scanner;

    public AdminService(MemberService memberService, TrainerService trainerService, 
                       AttendanceService attendanceService, Scanner scanner) {
        this.memberService = memberService;
        this.trainerService = trainerService;
        this.attendanceService = attendanceService;
        this.scanner = scanner;
    }

    public AdminService(AttendanceService attendanceService, MemberService memberService, Scanner scanner, TrainerService trainerService) {
        this.attendanceService = attendanceService;
        this.memberService = memberService;
        this.scanner = scanner;
        this.trainerService = trainerService;
    }

    public void showAdminDashboard() {
        while (true) {
            System.out.println("=== BẢNG ĐIỀU KHIỂN QUẢN TRỊ ===");
            System.out.println("1. Quản lý thành viên");
            System.out.println("2. Quản lý huấn luyện viên");
            System.out.println("3. Quản lý gói tập");
            System.out.println("4. Xem báo cáo doanh thu");
            System.out.println("5. Xem thống kê điểm danh");
            System.out.println("6. Đăng xuất");
            System.out.print("--> Chọn chức năng: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    manageMembers();
                    break;
                case 2:
                    manageTrainers();
                    break;
                case 3:
                    manageMembershipPlans();
                    break;
                case 4:
                    viewRevenueReports();
                    break;
                case 5:
                    viewAttendanceReports();
                    break;
                case 6:
                    System.out.println("Đã đăng xuất khỏi hệ thống.");
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ, vui lòng thử lại!");
            }
        }
    }

    private void manageMembers() {
        while (true) {
            System.out.println("\n=== QUẢN LÝ THÀNH VIÊN ===");
            System.out.println("1. Xem danh sách thành viên");
            System.out.println("2. Thêm thành viên mới");
            System.out.println("3. Cập nhật thông tin thành viên");
            System.out.println("4. Xóa thành viên");
            System.out.println("5. Quay lại");
            System.out.print("--> Chọn: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    listAllMembers();
                    break;
                case 2:
                    addNewMember();
                    break;
                case 3:
                    updateMember();
                    break;
                case 4:
                    deleteMember();
                    break;
                case 5:
                    return;
                default:
                    System.out.println(" Lựa chọn không hợp lệ!");
            }
        }
    }

    private void listAllMembers() {
        System.out.println("\n=== DANH SÁCH THÀNH VIÊN ===");
        List<Member> members = memberService.getAllMembers();
        if (members.isEmpty()) {
            System.out.println(" Hiện chưa có thành viên nào.");
            return;
        }
        
        System.out.printf("%-10s %-20s %-25s %-15s %-12s %-10s\n", 
            "ID", "Họ tên", "Email", "Gói tập", "Hết hạn", "Trạng thái");
        for (Member member : members) {
            System.out.printf("%-10s %-20s %-25s %-15s %-12s %-10s\n",
                member.getUserId(), member.getName(), member.getEmail(),
                member.getMembershipType(), member.getExpiryDate(), member.getStatus());
        }
    }

    private void addNewMember() {
        System.out.println("\n=== THÊM THÀNH VIÊN MỚI ===");
        System.out.print("Nhập họ tên: ");
        String name = scanner.nextLine();
        System.out.print("Nhập email: ");
        String email = scanner.nextLine();
        System.out.print("Nhập loại gói tập (BASIC/PREMIUM/VIP): ");
        String membershipType = scanner.nextLine();
        
        memberService.addMember(name, email, membershipType);
        System.out.println(" Thêm thành viên thành công!");
    }

    private void updateMember() {
        listAllMembers();
        System.out.print("Nhập ID thành viên cần cập nhật: ");
        String memberId = scanner.nextLine();
        
        Member member = memberService.findMemberById(memberId);
        if (member == null) {
            System.out.println(" Không tìm thấy thành viên!");
            return;
        }
        
        System.out.print("Nhập tên mới: ");
        String newName = scanner.nextLine();
        System.out.print("Nhập email mới: ");
        String newEmail = scanner.nextLine();
        System.out.print("Nhập loại gói tập mới: ");
        String newMembershipType = scanner.nextLine();

        boolean updated = memberService.updateMember(memberId, newName, newEmail, newMembershipType);
        if (updated) {
            System.out.println(" Cập nhật thành công!");
        } else {
            System.out.println(" Cập nhật thất bại!");
        }
    }

    private void deleteMember() {
        listAllMembers();
        System.out.print("Nhập ID thành viên cần xóa: ");
        String memberId = scanner.nextLine();
        
        System.out.print("Bạn có chắc chắn muốn xóa? (y/n): ");
        String confirm = scanner.nextLine();
        if (confirm.equalsIgnoreCase("y")) {
            boolean success = memberService.deleteMember(memberId);
            if (success) {
                System.out.println(" Xóa thành viên thành công!");
            } else {
                System.out.println(" Xóa thất bại!");
            }
        }
    }

    private void manageTrainers() {
        System.out.println("\n=== QUẢN LÝ HUẤN LUYỆN VIÊN ===");
        List<Trainer> trainers = trainerService.getAllTrainers();
        if (trainers.isEmpty()) {
            System.out.println(" Chưa có huấn luyện viên nào.");
            return;
        }
        for (Trainer trainer : trainers) {
            System.out.println("() " + trainer.getUserId() + " - " + trainer.getName() + " - " + trainer.getEmail());
        }
    }

    private void manageMembershipPlans() {
        System.out.println("\n=== DANH SÁCH GÓI TẬP ===");
        System.out.println("1. BASIC   - 500,000 VND/tháng");
        System.out.println("2. PREMIUM - 800,000 VND/tháng");
        System.out.println("3. VIP     - 1,200,000 VND/tháng");
    }

    private void viewAttendanceReports() {
        System.out.println("\n=== THỐNG KÊ ĐIỂM DANH ===");
        Map<String, Double> attendanceRates = attendanceService.getAttendanceRates();
        if (attendanceRates.isEmpty()) {
            System.out.println(" Chưa có dữ liệu điểm danh.");
            return;
        }
        for (Map.Entry<String, Double> entry : attendanceRates.entrySet()) {
            System.out.printf(" Thành viên %s: %.1f%%\n", entry.getKey(), entry.getValue());
        }
    }

    private void viewRevenueReports() {
        System.out.println("\n=== BÁO CÁO DOANH THU ===");
        double revenue = memberService.calculateTotalRevenue();
        System.out.printf(" Tổng doanh thu: %,.0f VND\n", revenue);
        
        int activeMembers = memberService.getActiveMembersCount();
        int expiredMembers = memberService.getExpiredMembersCount();
        System.out.println(" Thành viên đang hoạt động: " + activeMembers);
        System.out.println(" Thành viên hết hạn: " + expiredMembers);
    }
}
