package com.example.jwt_auth;

import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link User}
 */
@Value
public class UserDto implements Serializable {
    @NotNull(message = "Username cannot be blank")
    String username;
    @NotNull(message = "Email cannot be blank")
    String email;
    @NotNull(message = "Password cannot be blank")
    String password;

    @NotNull(message = "Confirm Password cannot be blank")
    String confirmPassword;

}