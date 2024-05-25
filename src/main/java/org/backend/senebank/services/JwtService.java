package org.backend.senebank.services;

import io.jsonwebtoken.Claims;
import org.backend.senebank.entities.User;

public interface JwtService {
    String generateToken(User user);

    String getEmail(String token);

    String getRole(String token);

    Claims getAllClaimsFromToken(String token);

    boolean validateToken(String token);
}
