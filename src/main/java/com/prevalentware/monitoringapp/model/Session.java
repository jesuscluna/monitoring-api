package com.prevalentware.monitoringapp.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "Session")
public class Session {
    @Id
    private String id;
    @Column(name = "sessionToken")
    private String sessionToken;
    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private User user;
    @Column(name = "expiresAt")
    private Timestamp expiresAt;
    @Column(name = "createdAt")
    private Timestamp createdAt;

    public Session() {}

    public Session(String id, String sessionToken, User user, Timestamp expiresAt, Timestamp createdAt) {
        this.id = id;
        this.sessionToken = sessionToken;
        this.user = user;
        this.expiresAt = expiresAt;
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSessionToken() {
        return sessionToken;
    }

    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Timestamp getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(Timestamp expiresAt) {
        this.expiresAt = expiresAt;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
