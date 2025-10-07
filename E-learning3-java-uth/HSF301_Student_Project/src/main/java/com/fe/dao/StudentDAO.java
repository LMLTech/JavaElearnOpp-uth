package com.fe.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import com.fe.pojo.Student;

public class StudentDAO {
    
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAs"); 

    
    
    public void save(Student student) {
    	EntityManager em = emf.createEntityManager(); 
    	try {
    		em.getTransaction().begin();
    		em.persist(student);
    		em.getTransaction().commit(); 
    	} catch (Exception ex) {
    		if (em.getTransaction().isActive()) {
    			em.getTransaction().rollback();
    		}
    		System.out.println("Error in save: " + ex.getMessage());
    	} finally {
    		if (em != null) {
    			em.close();
    		}
    	}
    }
    
    public Student findById(int studentID) {
    	EntityManager em = emf.createEntityManager();
    	Student student = null;
    	try {
    		student = em.find(Student.class, studentID);
    	} catch (Exception ex) {
    		System.out.println("Error in findById: " + ex.getMessage()); 
    	} finally {
    		if (em != null) {
    			em.close();
    		}
    	}
    	return student;
    }

    public List<Student> getStudents() {
    	EntityManager em = emf.createEntityManager();
    	List<Student> students = null;
    	try {
    		students = em.createQuery("from Student", Student.class).getResultList(); 
    	} catch (Exception ex) {
    		System.out.println("Error in getStudents: " + ex.getMessage());
    	} finally {
    		if (em != null) {
    			em.close();
    		}
    	}
    	return students;
    }
    
    public void delete(int studentID) { 
    	EntityManager em = emf.createEntityManager();
    	try {
    		em.getTransaction().begin();
    		// Chỉ tìm kiếm bằng int
    		Student s = em.find(Student.class, studentID); 
    		if (s != null) {
    			em.remove(s);
    		}
    		em.getTransaction().commit();
    	} catch (Exception ex) {
    		if (em.getTransaction().isActive()) {
    			em.getTransaction().rollback();
    		}
    		System.out.println("Error in delete: " + ex.getMessage());
    	} finally {
    		if (em != null) {
    			em.close();
    		}
    	}
    }

    public void update(Student student) {
    	EntityManager em = emf.createEntityManager();
    	try {
    		em.getTransaction().begin();
    		// Sử dụng merge để update entity
    		em.merge(student); 
    		em.getTransaction().commit();
    	} catch (Exception ex) {
    		if (em.getTransaction().isActive()) {
    			em.getTransaction().rollback();
    		}
    		System.out.println("Error in update: " + ex.getMessage());
    	} finally {
    		if (em != null) {
    			em.close();
    		}
    	}
    }
}