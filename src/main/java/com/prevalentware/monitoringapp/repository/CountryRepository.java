package com.prevalentware.monitoringapp.repository;

import com.prevalentware.monitoringapp.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, String> {
}
