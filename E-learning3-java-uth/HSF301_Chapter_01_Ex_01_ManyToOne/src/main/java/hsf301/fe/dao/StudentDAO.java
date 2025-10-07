package hsf301.fe.dao;

import hsf301.fe.pojo.Student; 
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence; 



public class StudentDAO {
	private static EntityManager em;
	private static EntityManagerFactory emf;
    
    //  Hàm tạo Constructor để khởi tạo EntityManagerFactory
	public StudentDAO(String persistenceName) {
		emf = Persistence.createEntityManagerFactory(persistenceName);
	}
    
    public StudentDAO() {
	}
	
  
    
	public void save(Student student) {
		try {
			em = emf.createEntityManager();
			em.getTransaction().begin();
			em.merge(student); // Dùng merge cho cả save và update
			em.getTransaction().commit();
		} catch (Exception ex) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
			System.out.println("Error in save: " + ex.getMessage());
		} finally {
            if (em != null) {
			    em.close();
            }
		}
	}
    
   

	public List<Student> getStudents(){
		List<Student> students = null;
		try {
			em = emf.createEntityManager();
			
		} catch (Exception ex) {
			System.out.println("Error in getStudents: " + ex.getMessage());
		} finally {
            if (em != null) {
			    em.close();
            }
		}
		return students;
	}
	
  

	public void delete(Long studentID) {
		try {
			em = emf.createEntityManager();
			em.getTransaction().begin(); 
            
        
			Student s = em.find(Student.class, studentID);
            if (s != null) {
                em.remove(s);
                em.getTransaction().commit(); 
                System.out.println("Student ID " + studentID + " deleted.");
            } else {
                 em.getTransaction().commit();
                 System.out.println("Student ID " + studentID + " not found.");
            }
            
		} catch (Exception ex) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
			System.out.println("Error in delete: " + ex.getMessage());
		} finally {
            if (em != null) {
			    em.close();
            }
		}
	}
	 
  
	public Student findById(Long studentID) {
		Student student = null;
		try {
			em = emf.createEntityManager();
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
    

	public void update(Student student) {
		try {
			em = emf.createEntityManager();
			em.getTransaction().begin();
			
            // Sử dụng merge cho update
            em.merge(student);
            
            // Commit giao dịch
            em.getTransaction().commit();
			
		} catch (Exception ex) {
            if (em != null && em.getTransaction().isActive()) {
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