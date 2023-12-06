package com.prevalentware.monitoringapp.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "User")
public class User {
    @Id
    private String id;
    @Column
    private String email;
    @Column(name = "emailVerified")
    private Timestamp emailVerified;
    @Column(name = "termsAndConditionsAccepted")
    private Timestamp termsAndConditionsAccepted;
    @Column
    private String name;
    @Column
    private String image;
    @Column
    private String position;
    @Column(name = "createdAt")
    private Timestamp createdAt;
    @Column(name = "updatedAt")
    private Timestamp updatedAt;
    @ManyToOne
    @JoinColumn(name = "roleId", referencedColumnName = "id")
    private Role role;

    public User() {}

    public User(String id, String email, Timestamp emailVerified, Timestamp termsAndConditionsAccepted, String name, String image, String position, Timestamp createdAt, Timestamp updatedAt, Role role) {
        this.id = id;
        this.email = email;
        this.emailVerified = emailVerified;
        this.termsAndConditionsAccepted = termsAndConditionsAccepted;
        this.name = name;
        this.image = image;
        this.position = position;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Timestamp getEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(Timestamp emailVerified) {
        this.emailVerified = emailVerified;
    }

    public Timestamp getTermsAndConditionsAccepted() {
        return termsAndConditionsAccepted;
    }

    public void setTermsAndConditionsAccepted(Timestamp termsAndConditionsAccepted) {
        this.termsAndConditionsAccepted = termsAndConditionsAccepted;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
