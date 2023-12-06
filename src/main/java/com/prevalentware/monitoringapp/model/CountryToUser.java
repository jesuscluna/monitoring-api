package com.prevalentware.monitoringapp.model;

import javax.persistence.*;

@Entity
@Table(name = "_CountryToUser")
public class CountryToUser {
    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "A", referencedColumnName = "id")
    private Country country;

    @ManyToOne
    @JoinColumn(name = "B", referencedColumnName = "id")
    private User user;
}
