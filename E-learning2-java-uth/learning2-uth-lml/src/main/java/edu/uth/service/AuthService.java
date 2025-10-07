package edu.uth.service;


import edu.uth.model.User;

import java.util.*;

public class AuthService {
    private List<User> users;
    
    public AuthService() {
        this.users = FileHandler.loadUsers();
    }
    
    public User login(String email, String password) {
        for (User user : users) {
            if (user.getEmail().equals(email) && user.authenticate(password)) {
                return user;
            }
        }
        return null;
    }
    
    public List<User> getUsers() {
        return users;
    }
}