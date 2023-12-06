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
public class CountryDTO {
    private String name;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
