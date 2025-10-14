package hsf301.fe.gui;

import hsf301.fe.pojo.Book;
import hsf301.fe.pojo.Student;
import hsf301.fe.service.IStudentService;
import hsf301.fe.service.StudentService;

public class ManyToMany {

    public static void main(String[] args) {
        String fileName = "hibernate.cfg.xml";
        IStudentService studentService = new StudentService(fileName);

        // Tạo 1 sinh viên
        Student student1 = new Student("Lam", "Nguyen", 9);

        // Tạo 1 cuốn sách
        Book book1 = new Book("Java Persistence with Spring", "Catalin Tudose", "9781617299186");
        
        // Thiết lập mối quan hệ 
        // Sinh viên Lam mượn sách Java và Clean Code
        student1.getBooks().add(book1);
        

        // Lưu cả 1 sinh viên (sách và bảng trung gian sẽ được lưu theo)
        studentService.save(student1);
        
        System.out.println("Học sinh và sách đã được lưu thành công với mối quan hệ Nhiều-Nhiều!");
    }
}