package hsf301.fe.service;

import java.util.List;
import hsf301.fe.pojo.Student; 

public interface IStudentService {
    public List<Student> findAll();
    public void save(Student student);
    public void delete(Long studentID);
    public Student findById(Long studentID);
    public void update(Student student);
}