package hsf301.fe.gui;
import hsf301.fe.dao.StudentDAO;      
import hsf301.fe.pojo.Books;          
import hsf301.fe.pojo.Student;        
import java.util.HashSet;    
import java.util.Set; 

public class ManyToManyDemo { 
    public static void main(String[] args) {
        
        String persistenceUnitName = "JPAS"; 
        StudentDAO studentDAO = new StudentDAO(persistenceUnitName);
        Student student1 = new Student("Lam", "Nguyen", 9);
        Books book1 = new Books("Java Persistence with Spring", "Catalin Tudose", "9781617299186");         
        //  Student1 đọc Book1 và Book2
        student1.getBooks().add(book1);
       
        // Book1 được đọc bởi Student1
        Set<Student> studentsForBook1 = new HashSet<>();
        studentsForBook1.add(student1);
        book1.setStudents(studentsForBook1);             
        studentDAO.save(student1);
        System.out.println("Save 1 Students and 1 Books with Many-to-Many success.");
        System.out.println("Kiểm tra bảng STUDENTS_BOOKS trong cơ sở dữ liệu để xác nhận.");
    }
}