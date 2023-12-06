package com.prevalentware.monitoringapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserMonitoringDTO {
    private Integer usage;
    private String description;
    private String userName;
    private Timestamp createdAt;
}
