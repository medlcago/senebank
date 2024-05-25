package org.backend.senebank.dto.response;

import lombok.Getter;

@Getter
public class JwtResponse {
    private final String access_token;
    private final String token_type;

    public JwtResponse(String token) {
        this.access_token = token;
        this.token_type = "Bearer";
    }
}