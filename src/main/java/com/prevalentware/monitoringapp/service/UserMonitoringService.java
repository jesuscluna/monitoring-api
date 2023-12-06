package com.prevalentware.monitoringapp.service;

import com.prevalentware.monitoringapp.dto.UserDTO;
import com.prevalentware.monitoringapp.dto.UserMonitoringDTO;

import java.util.List;

public interface UserMonitoringService {
    List<UserMonitoringDTO> getUserMonitoringByTimeRange(String userEmail, String startDate, String endDate, int page, int size, String token) throws  Exception;
    List<UserDTO> getTop3UsersWithMostRecords(String startDate, String endDate) throws Exception;
    List<UserDTO> getTopUsersByEventTypeAndCountry(String eventType, String countryId, String startDate, String endDate, int page, int size) throws Exception;
}
