package hsf301.fe.gui;

import hsf301.fe.pojo.Book;
import hsf301.fe.pojo.Student;
import hsf301.fe.service.IStudentService;
import hsf301.fe.service.StudentService;

public class ManyToOne {

    public static void main(String[] args) {
        // Tên file cấu hình Hibernate
        String fileName = "hibernate.cfg.xml";
        IStudentService studentService = new StudentService(fileName);

        // Tạo một đối tượng Student mới bằng constructor
        Student student = new Student("Lam", "Nguyen", 9);

        // Tạo một đối tượng Book mới bằng constructor
        Book book = new Book("Java Persistence with Spring", "Catalin Tudose", "9781617299186");
        
        // Thêm cuốn sách vào danh sách sách của sinh viên
        student.getBooks().add(book);
        
        // Thiết lập mối quan hệ ngược lại từ Book and Student
        book.setStudent(student);

   
        studentService.save(student);
        
        System.out.println("Học sinh và sách liên quan đã được lưu thành công!");
    }
}