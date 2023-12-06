package com.prevalentware.monitoringapp.repository;

import com.prevalentware.monitoringapp.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<Session, String> {
    Session findBySessionToken(String token);
}
