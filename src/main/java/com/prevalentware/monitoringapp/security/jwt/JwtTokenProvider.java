package com.prevalentware.monitoringapp.security.jwt;

import com.prevalentware.monitoringapp.model.User;
import com.prevalentware.monitoringapp.security.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {

    @Autowired
    private AuthService authService;

    public boolean validateToken(String token) {
        return authService.authenticate(token);
    }

    public String getUsernameFromToken(String token) {
        User user = authService.getAuthenticatedUser(token);
        return user.getEmail();
    }
}
