package com.fe.main;

import java.util.Scanner;
import java.util.List;
import com.fe.dao.StudentDAO; 
import com.fe.pojo.Student; 

public class StudentMain {
    public static void main(String[] args) {
        
        // Khởi tạo DAO và Scanner
        StudentDAO studentDAO = new StudentDAO(); 
        Scanner console = new Scanner(System.in);

        int inputKey = -1;
        while (inputKey != 0) {
            System.out.println("\n***********MENU*************");
            System.out.println("1. Add Student");
            System.out.println("2. Get All Students"); 
            System.out.println("3. Delete Student (ID = 1)"); 
            System.out.println("4. Update Student (ID = 1)"); 
            System.out.println("5. Find Student by ID (ID = 1)"); 
            System.out.println("0. QUIT");
            System.out.println("***********END*************");

            System.out.print("Enter your choice: ");
            
            if (console.hasNextInt()) {
                inputKey = console.nextInt();
                console.nextLine(); 

                switch (inputKey) {
                    case 0:
                        System.out.println("Exiting...");
                        break;
                    
                    case 1: // Add Student
                        Student newStudent = new Student("Lam", "Nguyen", 9);
                        studentDAO.save(newStudent);
                        System.out.println("Student saved."); 
                        break;
                        
                    case 2: // Get All Students
                        List<Student> students = studentDAO.getStudents();
                        students.forEach(System.out::println);
                        break;
                        
                    case 3: // Delete Student (ID = 1)
                        studentDAO.delete(1);
                        System.out.println("Student ID 1 deleted."); 
                        break;
                        
                    case 4: // Update Student (ID = 1)
                        Student updateStudent = new Student(1, "Sang", "Nguyen", 9);
                        studentDAO.update(updateStudent);
                        System.out.println("Student ID 1 updated."); 
                        break;
                        
                    case 5: // Find Student by ID (ID = 1)
                        Student foundStudent = studentDAO.findById(1);
                        if (foundStudent != null) {
                            System.out.println("Found: " + foundStudent); 
                        } else { 
                            System.out.println("Student ID 1 not found.");
                        }
                        break;
                        
                    default:
                        System.out.println("Invalid input. Please try again.");
                }
            } else {
                System.out.println("Invalid input. Enter a number.");
                console.next(); 
            }
        }
        console.close();
    } 
}