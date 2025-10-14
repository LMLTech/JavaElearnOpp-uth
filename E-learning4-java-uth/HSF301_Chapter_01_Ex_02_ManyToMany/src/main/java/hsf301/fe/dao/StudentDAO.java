package hsf301.fe.dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import hsf301.fe.pojo.Student; 

public class StudentDAO {
    private SessionFactory sessionFactory = null;
    private Configuration cf = null;

    // Constructor để khởi tạo SessionFactory từ file hibernate.cfg.xml
    public StudentDAO(String persistenceName) {
        cf = new Configuration();
        cf = cf.configure(persistenceName); // Tên file cấu hình, vd: "hibernate.cfg.xml"
        sessionFactory = cf.buildSessionFactory();
    }

   
    public void save(Student student) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(student);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

   
    public List<Student> getStudents() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Student", Student.class).list();
        }
    }

    
    public void delete(int studentID) {
        Transaction transaction = null;
        Student student = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            student = session.get(Student.class, studentID);
            if (student != null) {
                session.delete(student);
                System.out.println("Student is deleted");
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    
    public Student findById(int studentID) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Student.class, studentID);
        }
    }

    
    public void update(Student student) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(student);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}