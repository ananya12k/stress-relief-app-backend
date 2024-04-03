package com.example.jwt_auth;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotEmpty(message = "First name cannot be blank")
    private String firstName;

    @NotEmpty(message = "Last name cannot be blank")
    private String lastName;

    @NotEmpty(message = "Username cannot be blank")
    private String username;

    @NotEmpty(message = "Email cannot be blank")
    private String email;

    @NotEmpty(message = "Password cannot be blank")
    private String password;

    @NotEmpty(message = "Confirm Password cannot be blank")
    private String confirmPassword;

    String token;

    public String getLogin() {
        return username; // Assuming username is used for login
    }

    public void setToken(String token) {
        this.token = token;
    }

    @AssertTrue(message = "Passwords do not match")
    public boolean isPasswordConfirmed() {
        // Check if password and confirm password match
        return password != null && password.equals(confirmPassword);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getToken() {
        return token;
    }
}
