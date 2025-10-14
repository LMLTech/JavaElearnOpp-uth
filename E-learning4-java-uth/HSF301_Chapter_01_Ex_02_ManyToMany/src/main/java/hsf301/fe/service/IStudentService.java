package hsf301.fe.service;

import hsf301.fe.pojo.Student; 
import java.util.List;

public interface IStudentService {
    
    public List<Student> findAll();
    
    public Student findById(int studentID);
    
    public void save(Student student);
    
    public void update(Student student);
    
    public void delete(int studentID);
    
}