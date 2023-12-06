package com.prevalentware.monitoringapp.service;

import com.prevalentware.monitoringapp.dto.CountryDTO;

import java.util.List;

public interface CountryService {
    List<CountryDTO> getAllCountries(int page, int size) throws Exception;
}
