package edu.uth.model;

public class Trainer extends User{

    public Trainer(String userId, String name, String email, String password) {
        super(userId, name, email, password, "TRAINER");
    }
    
}
