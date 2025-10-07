package edu.uth.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import edu.uth.model.Admin;
import edu.uth.model.Member;
import edu.uth.model.Trainer;
import edu.uth.model.User;

public class FileHandler {
    private static final String USERS_FILE = "database/users.csv";
 
    
    // Tao thu muc data neu chua ton tai
    static {
        new File("database").mkdirs();
    }
    
    // Doc danh sach user tu csv
    public static List<User> loadUsers() {
        List<User> users = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(USERS_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 5) {
                    String role = data[4];
                    switch (role) {
                        case "ADMIN":
                            users.add(new Admin(data[0], data[1], data[2], data[3]));
                            break;
                        case "TRAINER":
                            users.add(new Trainer(data[0], data[1], data[2], data[3]));
                            break;
                        case "MEMBER":
                            LocalDate joinDate = LocalDate.parse(data[5]);
                            LocalDate expiryDate = LocalDate.parse(data[7]);
                            users.add(new Member(data[0], data[1], data[2], data[3], 
                                               joinDate, data[6], expiryDate));
                            break;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Khong tim thay file user mau...");
            createSampleData();
        }
        return users;
    }
    
    // Luu user 
    public static void saveUsers(List<User> users) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(USERS_FILE))) {
            for (User user : users) {
                if (user instanceof Admin) {
                    pw.println(user.getUserId() + "," + user.getName() + "," + 
                              user.getEmail() + "," + "password" + ",ADMIN");
                } else if (user instanceof Trainer) {
                    pw.println(user.getUserId() + "," + user.getName() + "," + 
                              user.getEmail() + "," + "password" + ",TRAINER");
                } else if (user instanceof Member member) {
                    pw.println(member.getUserId() + "," + member.getName() + "," + 
                              member.getEmail() + "," + "password" + ",MEMBER," +
                              LocalDate.now() + "," + member.getMembershipType() + "," + 
                              member.getExpiryDate());
                }
            }
        } catch (IOException e) {
            System.out.println("Error saving users: " + e.getMessage());
        }
    }
    
    // Du lieu mau
    private static void createSampleData() {
        List<User> sampleUsers = new ArrayList<>();
        sampleUsers.add(new Admin("A01", "Admin User", "admin@gym.com", "admin123"));
        sampleUsers.add(new Trainer("T01", "John Trainer", "trainer@gym.com", "trainer123"));
        sampleUsers.add(new Member("M01", "Mike Member", "member@gym.com", "member123",
                                 LocalDate.now(), "PREMIUM", LocalDate.now().plusMonths(1)));
        saveUsers(sampleUsers);
    }
}