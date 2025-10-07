package edu.uth.model;

import java.time.LocalDate;

public class Member extends User {
    private final LocalDate joinDate;
    private final String membershipType;
    private LocalDate expiryDate;
    private String status; // ACTIVE, EXPIRED
    
    public Member(String userId, String name, String email, String password, 
                  LocalDate joinDate, String membershipType, LocalDate expiryDate) {
        super(userId, name, email, password, "MEMBER");
        this.joinDate = joinDate;
        this.membershipType = membershipType;
        this.expiryDate = expiryDate;
        this.status = expiryDate.isAfter(LocalDate.now()) ? "ACTIVE" : "EXPIRED";
    }
    
    
    //Getter & Setter
    public LocalDate getExpiryDate() { return expiryDate; }
    public String getStatus() { return status; }
    public String getMembershipType() { return membershipType; }
    
    public LocalDate getJoinDate() { return joinDate; }
    
    public void renewMembership(int months) {
        this.expiryDate = this.expiryDate.plusMonths(months);
        this.status = "ACTIVE";
    }
}
