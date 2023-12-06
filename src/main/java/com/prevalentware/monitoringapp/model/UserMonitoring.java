package com.prevalentware.monitoringapp.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "UserMonitoring", schema = "public")
public class UserMonitoring {
    @Id
    private String id;
    @Column
    private Integer usage;
    @Column
    private String description;
    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private User user;
    @Column(name = "createdAt")
    private Timestamp createdAt;

    public UserMonitoring() {}

    public UserMonitoring(String id, int usage, String description, User user, Timestamp createdAt) {
        this.id = id;
        this.usage = usage;
        this.description = description;
        this.user = user;
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getUsage() {
        return usage;
    }

    public void setUsage(int usage) {
        this.usage = usage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
