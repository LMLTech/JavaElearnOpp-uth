package hsf301.fe.pojo;

import java.util.HashSet;
import java.util.Set; 
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "STUDENTS")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id; 

    @Column(name = "firstName", nullable = false) 
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "marks")
    private int marks;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL) 
    private Set<Books> books = new HashSet<>(); 

    public Student() {
    }

  
    public Student(String firstName, String lastName, int marks) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.marks = marks;
    }

    
    public Student(Long id, String firstName, String lastName, int marks) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.marks = marks;
    }
    
  
    public void setBooks(Set<Books> books) {
        this.books = books;
    }
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public int getMarks() { return marks; }
    public void setMarks(int marks) { this.marks = marks; }
    public Set<Books> getBooks() { return books; }
    
    @Override
    public String toString() {
        return "Student [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", marks=" + marks + "]";
    }

	public void setBooks(Object books2) {

		
	}
}