package com.prevalentware.monitoringapp.controller;

import com.prevalentware.monitoringapp.dto.CountryDTO;
import com.prevalentware.monitoringapp.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("/api/countries")
public class CountryController {
    @Autowired
    private CountryService countryService;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('Admin', 'Manager')")
    public ResponseEntity<List<CountryDTO>> getAllCountries(@Min(0) @RequestParam() int page, @Min(0) @RequestParam int size) throws Exception {
        return new ResponseEntity<>(countryService.getAllCountries(page, size), HttpStatus.OK);
    }
}
