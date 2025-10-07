package hsf301.fe.repository;

import java.util.List;
import hsf301.fe.pojo.Student;   
import hsf301.fe.dao.StudentDAO; 

public class StudentRepository implements IStudentRepository {
    private StudentDAO studentDAO = null;

    public StudentRepository(String fileConfig) {
        studentDAO = new StudentDAO(fileConfig);
    }

    @Override
    public void save(Student student) {
        // TODO Auto-generated method stub
        studentDAO.save(student);
    }

    @Override
    public List<Student> findAll() {
        // TODO Auto-generated method stub
        return studentDAO.getStudents();
    }

    @Override
    public void delete(Long studentID) {
        studentDAO.delete(studentID);
    }

    @Override
    public Student findById(Long studentID) {
        // TODO Auto-generated method stub
        return studentDAO.findById(studentID);
    }

    @Override
    public void update(Student student) {
        studentDAO.update(student);
    }
}