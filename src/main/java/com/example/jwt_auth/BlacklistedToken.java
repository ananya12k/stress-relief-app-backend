package com.example.jwt_auth;

import jakarta.persistence.*;

@Entity
@Table(name = "blacklistedtokens")
public class BlacklistedToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "token")
    private String token;

    public BlacklistedToken() {
        // Default constructor required by JPA
    }

    public BlacklistedToken(String token) {
        this.token = token;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
