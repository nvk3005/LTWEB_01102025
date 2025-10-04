package org.example.jwt.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
    private String token;
    private String expiresIn;
    public LoginResponse(String token) {
        this.token = token;
        this.expiresIn = null;
    }
}
