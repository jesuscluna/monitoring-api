package com.prevalentware.monitoringapp.controller;

import com.prevalentware.monitoringapp.dto.UserDTO;
import com.prevalentware.monitoringapp.dto.UserMonitoringDTO;
import com.prevalentware.monitoringapp.service.UserMonitoringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("/api/user-monitoring")
public class UserMonitoringController {
    @Autowired
    private UserMonitoringService userMonitoringService;

    @GetMapping("/user")
    public ResponseEntity<List<UserMonitoringDTO>> getUserMonitoringByTimeRange(@RequestParam String userEmail, @RequestParam String startDate, @RequestParam String endDate, @Min(0) @RequestParam int page, @Min(0) @RequestParam int size, @RequestHeader("Authorization") String token) throws Exception {
        return new ResponseEntity<>(userMonitoringService.getUserMonitoringByTimeRange(userEmail, startDate, endDate, page, size, token), HttpStatus.OK);
    }

    @GetMapping("/top-users")
    @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity<List<UserDTO>> getTop3UsersWithMostRecords(@RequestParam String startDate, @RequestParam String endDate) throws Exception {
        return new ResponseEntity<>(userMonitoringService.getTop3UsersWithMostRecords(startDate, endDate), HttpStatus.OK);
    }

    @GetMapping("/top-users-by-event-type")
    @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity<List<UserDTO>> getTopUsersByEventTypeAndCountry(@RequestParam String eventType, @RequestParam String countryId, @RequestParam String startDate, @RequestParam String endDate, @Min(0) @RequestParam int page, @Min(0) @RequestParam int size) throws Exception {
        return new ResponseEntity<>(userMonitoringService.getTopUsersByEventTypeAndCountry(eventType, countryId, startDate, endDate, page, size), HttpStatus.OK);
    }
}
