package com.prevalentware.monitoringapp.service;

import com.prevalentware.monitoringapp.common.GuardClauses;
import com.prevalentware.monitoringapp.common.LogUtils;
import com.prevalentware.monitoringapp.common.TimeUtils;
import com.prevalentware.monitoringapp.dto.UserDTO;
import com.prevalentware.monitoringapp.dto.UserMonitoringDTO;
import com.prevalentware.monitoringapp.errors.CustomException;
import com.prevalentware.monitoringapp.errors.InternalServerException;
import com.prevalentware.monitoringapp.model.User;
import com.prevalentware.monitoringapp.model.UserMonitoring;
import com.prevalentware.monitoringapp.repository.UserMonitoringRepository;
import com.prevalentware.monitoringapp.security.service.AuthService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserMonitoringServiceImpl implements  UserMonitoringService {
    @Autowired
    private UserMonitoringRepository userMonitoringRepository;
    @Autowired
    private AuthService authService;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public List<UserMonitoringDTO> getUserMonitoringByTimeRange(String userEmail, String startDate, String endDate, int page, int size, String token) throws Exception, CustomException {
        List<UserMonitoringDTO> responseDTO = new ArrayList<>();
        User authenticatedUser = authService.getAuthenticatedUser(token);
        GuardClauses.guardAgainstUnauthorizedUserEmail(authenticatedUser, userEmail);
        try {
            Date start = TimeUtils.parseDate(startDate);
            Date end = TimeUtils.parseDate(endDate);
            Pageable pageable = PageRequest.of(page, size);
            Page<UserMonitoring> userMonitoring = userMonitoringRepository.findByUserEmailAndCreatedAtBetween(userEmail, start, end, pageable);
            userMonitoring.forEach(userMonitored -> responseDTO.add(modelMapper.map(userMonitored, UserMonitoringDTO.class)));
            return responseDTO;
        } catch (Exception e) {
            LogUtils.catchThrowable(e);
            throw new InternalServerException(e.getMessage());
        }
    }

    @Override
    public List<UserDTO> getTop3UsersWithMostRecords(String startDate, String endDate) throws Exception {
        List<UserDTO> responseDTO = new ArrayList<>();
        try {
            Date start = TimeUtils.parseDate(startDate);
            Date end = TimeUtils.parseDate(endDate);
            List<User> topUsers = userMonitoringRepository.findTop3UsersWithMostRecords(start, end, PageRequest.of(0,3));
            topUsers.forEach(topUser -> responseDTO.add(modelMapper.map(topUser, UserDTO.class)));
            return responseDTO;
        } catch (Exception e) {
            LogUtils.catchThrowable(e);
            throw new InternalServerException(e.getMessage());
        }
    }

    @Override
    public List<UserDTO> getTopUsersByEventTypeAndCountry(String eventType, String countryId, String startDate, String endDate, int page, int size) throws Exception {
        List<UserDTO> responseDTO = new ArrayList<>();
        try {
            Date start = TimeUtils.parseDate(startDate);
            Date end = TimeUtils.parseDate(endDate);Pageable pageable = PageRequest.of(page, size);
            Page<User> topUsers = userMonitoringRepository.findTopUsersByEventTypeAndCountry(countryId, eventType, start, end, pageable);
            topUsers.forEach(topUser -> responseDTO.add(modelMapper.map(topUser, UserDTO.class)));
            return responseDTO;
        } catch (Exception e) {
            LogUtils.catchThrowable(e);
            throw new InternalServerException(e.getMessage());
        }
    }
}
