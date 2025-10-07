package com.fe.pojo; 

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "STUDENTS")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "firstName", nullable = false, unique = false)
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "marks")
    private int marks;

    public Student() {
    }

    // Sửa lỗi: constructor 3 tham số phải gán giá trị
    public Student(String firstName, String lastName, int marks) {
    	this.firstName = firstName;
    	this.lastName = lastName;
    	this.marks = marks;
    }
    
    // Thêm constructor đầy đủ tham số (cần cho update)
    public Student(int id, String firstName, String lastName, int marks) {
    	this.id = id;
    	this.firstName = firstName;
    	this.lastName = lastName;
    	this.marks = marks;
    }

    // Bắt buộc phải có các getter/setter
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getMarks() {
		return marks;
	}

	public void setMarks(int marks) {
		this.marks = marks;
	}
	
	@Override
	public String toString() {
		return "Student [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", marks=" + marks + "]";
	}
}