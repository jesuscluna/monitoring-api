package com.prevalentware.monitoringapp.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.prevalentware.monitoringapp.model.RoleNameEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    @JsonIgnore
    private String id;
    private String email;
    private Timestamp emailVerified;
    private Timestamp termsAndConditionsAccepted;
    private String name;
    private String image;
    private String position;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private RoleNameEnum roleName;
}
