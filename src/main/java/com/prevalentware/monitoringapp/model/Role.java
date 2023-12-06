package com.prevalentware.monitoringapp.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "Role")
public class Role {
    @Id
    private String id;
    @Column
    @Enumerated(EnumType.STRING)
    private RoleNameEnum name;
    @Column(name = "createdAt")
    private Timestamp createdAt;

    public Role() {}

    public Role(String id, RoleNameEnum name, Timestamp createdAt) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public RoleNameEnum getName() {
        return name;
    }

    public void setName(RoleNameEnum name) {
        this.name = name;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
