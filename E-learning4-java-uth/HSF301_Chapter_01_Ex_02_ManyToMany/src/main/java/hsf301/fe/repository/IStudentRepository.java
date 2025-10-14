package hsf301.fe.repository;

import hsf301.fe.pojo.Student; 
import java.util.List;

public interface IStudentRepository {
    
    public List<Student> findAll();
    
    public Student findById(int studentID);
    
    public void save(Student student);
    
    public void update(Student student);
    
    public void delete(int studentID);
    
}