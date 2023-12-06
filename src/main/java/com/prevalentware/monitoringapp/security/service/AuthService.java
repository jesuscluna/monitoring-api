package com.prevalentware.monitoringapp.security.service;

import com.prevalentware.monitoringapp.common.TokenUtils;
import com.prevalentware.monitoringapp.model.Session;
import com.prevalentware.monitoringapp.model.User;
import com.prevalentware.monitoringapp.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class AuthService {
    @Autowired
    private SessionRepository sessionRepository;

    public boolean authenticate(String token) {
        Session session = sessionRepository.findBySessionToken(TokenUtils.removeBearerPrefix(token));
        return session != null && session.getExpiresAt().toInstant().isAfter(Instant.now());
    }

    public User getAuthenticatedUser(String token) {
        Session session = sessionRepository.findBySessionToken(TokenUtils.removeBearerPrefix(token));

        if (session != null && session.getExpiresAt().toInstant().isAfter(Instant.now())) {
            return session.getUser();
        }
        return null;
    }
}
