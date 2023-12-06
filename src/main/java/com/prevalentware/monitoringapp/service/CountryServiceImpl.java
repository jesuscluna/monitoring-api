package com.prevalentware.monitoringapp.service;

import com.prevalentware.monitoringapp.common.LogUtils;
import com.prevalentware.monitoringapp.dto.CountryDTO;
import com.prevalentware.monitoringapp.errors.InternalServerException;
import com.prevalentware.monitoringapp.errors.NotAcceptableException;
import com.prevalentware.monitoringapp.model.Country;
import com.prevalentware.monitoringapp.repository.CountryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {
    @Autowired
    private CountryRepository countryRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public List<CountryDTO> getAllCountries(int page, int size) throws Exception {
        List<CountryDTO> responseDTO = new ArrayList<>();
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<Country> countries = countryRepository.findAll(pageable);
            countries.forEach(country -> responseDTO.add(modelMapper.map(country, CountryDTO.class)));
            return responseDTO;
        } catch (IllegalArgumentException illegalArgumentException) {
            LogUtils.catchThrowable(illegalArgumentException);
            throw new NotAcceptableException(illegalArgumentException.getMessage());
        } catch (Exception e) {
            LogUtils.catchThrowable(e);
            throw new InternalServerException(e.getMessage());
        }
    }
}
