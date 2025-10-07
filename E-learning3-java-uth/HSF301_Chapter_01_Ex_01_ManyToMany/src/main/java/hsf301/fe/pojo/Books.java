package hsf301.fe.pojo;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany; 
import javax.persistence.Table;

@Entity
@Table(name = "BOOKS")
public class Books {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "title", length = 30)
    private String title;

    private String author;

    private String isbn;

   
    @ManyToMany(mappedBy = "books", cascade = CascadeType.ALL)
    private Set<Student> students = new HashSet<>(); 
    
    public Books() { 
    }
    
    // Constructor 
    public Books(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
    }
    
    // Getters và Setters
    public Set<Student> getStudents() { 
        return students;
    }

    public void setStudents(Set<Student> students) { 
        this.students = students;
    }
    

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }
    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }
}