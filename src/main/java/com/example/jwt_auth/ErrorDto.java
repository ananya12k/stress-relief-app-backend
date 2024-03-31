package com.example.jwt_auth;

import lombok.Builder;

//@Getter
//@Setter
@Builder
public class ErrorDto {

    String message;

    public ErrorDto(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
