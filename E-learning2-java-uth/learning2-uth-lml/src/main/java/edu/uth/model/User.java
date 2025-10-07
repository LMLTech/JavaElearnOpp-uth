package edu.uth.model;

public abstract class User {
    protected String userId;
    protected String name;
    protected String email;
    protected String password;
    protected String role;

    // constructor
    public User(String userId, String name, String email, String password, String role) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    // getter setter 
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
        
    public boolean authenticate(String password){
        return this.password.equals(password);
    }

    public void showDashboard() {
        System.out.println("\n=== " + role + " DASHBOARD ===");
        System.out.println("Xin chao: " + name);
    }
}
