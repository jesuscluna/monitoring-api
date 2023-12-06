package com.prevalentware.monitoringapp.service;

import com.prevalentware.monitoringapp.common.TimeUtils;
import com.prevalentware.monitoringapp.dto.UserDTO;
import com.prevalentware.monitoringapp.model.User;
import com.prevalentware.monitoringapp.repository.UserMonitoringRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class UserMonitoringServiceImplTest {
    @Mock
    private UserMonitoringRepository userMonitoringRepository;
    @Mock
    private ModelMapper modelMapper;
    @InjectMocks
    private UserMonitoringServiceImpl userMonitoringService;

    @Test
    void getTopUsersByEventTypeAndCountry() throws Exception {
        String eventType = "test";
        String countryId = "1";
        String startDate = "2010-05-05";
        String endDate = "2023-11-29";
        int page = 0;
        int size = 10;

        List<UserDTO> expectedResponse = Arrays.asList(new UserDTO(), new UserDTO());

        Mockito.when(TimeUtils.parseDate(ArgumentMatchers.anyString())).thenReturn(new Date());
        Mockito.when(userMonitoringRepository.findTopUsersByEventTypeAndCountry(ArgumentMatchers.anyString(), ArgumentMatchers.anyString(), ArgumentMatchers.any(Date.class), ArgumentMatchers.any(Date.class), ArgumentMatchers.any(Pageable.class)))
                .thenReturn(new PageImpl<>(Collections.singletonList(new User())));
        Mockito.when(modelMapper.map(ArgumentMatchers.any(User.class), ArgumentMatchers.eq(UserDTO.class))).thenReturn(new UserDTO());

        List<UserDTO> actualResponse = userMonitoringService.getTopUsersByEventTypeAndCountry(eventType, countryId, startDate, endDate, page, size);

        assertNotNull(actualResponse);
        assertEquals(expectedResponse.size(), actualResponse.size());
    }
}