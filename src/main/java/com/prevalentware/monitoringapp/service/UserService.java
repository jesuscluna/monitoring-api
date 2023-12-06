package com.prevalentware.monitoringapp.service;

import com.prevalentware.monitoringapp.dto.UserDTO;

import java.util.List;

public interface UserService {
    List<UserDTO> getAllUsers(int page, int size, String token) throws Exception;
    UserDTO getUserByEmail(String email, String token) throws Exception;
}
