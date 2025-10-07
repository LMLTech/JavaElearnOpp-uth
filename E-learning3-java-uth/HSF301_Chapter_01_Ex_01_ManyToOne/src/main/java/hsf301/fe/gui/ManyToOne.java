package hsf301.fe.gui;

import hsf301.fe.dao.StudentDAO;      
import hsf301.fe.pojo.Books;          
import hsf301.fe.pojo.Student;        
import java.util.HashSet;    

public class ManyToOne { 
    public static void main(String[] args) {
        
        String persistenceUnitName = "JPAS"; 
        StudentDAO studentDAO = new StudentDAO(persistenceUnitName);
        Student student = new Student("Lam", "Nguyen", 9);
        Books book = new Books("Java Persistence with Spring", "Catalin Tudose", "9781617299186");         
        // Thiết lập liên kết hai chiều 
        book.setStudent(student); 
        if (student.getBooks() == null) {
            // Khởi tạo HashSet là Set để khớp với kiểu dữ liệu trong Student.java
            student.setBooks(new HashSet<Books>()); 
        }
        // Thiết lập liên kết ngược OneToMany
        student.getBooks().add(book);
        studentDAO.save(student);
        System.out.println("Save Student and Book success.");
    }
}