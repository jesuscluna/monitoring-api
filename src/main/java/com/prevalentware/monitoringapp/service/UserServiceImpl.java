package com.prevalentware.monitoringapp.service;

import com.prevalentware.monitoringapp.common.GuardClauses;
import com.prevalentware.monitoringapp.common.LogUtils;
import com.prevalentware.monitoringapp.dto.UserDTO;
import com.prevalentware.monitoringapp.errors.CustomException;
import com.prevalentware.monitoringapp.errors.InternalServerException;
import com.prevalentware.monitoringapp.model.RoleNameEnum;
import com.prevalentware.monitoringapp.model.User;
import com.prevalentware.monitoringapp.repository.UserRepository;
import com.prevalentware.monitoringapp.security.service.AuthService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthService authService;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<UserDTO> getAllUsers(int page, int size, String token) throws Exception {
        List<UserDTO> responseDTO = new ArrayList<>();
        try {
            User authenticatedUser = authService.getAuthenticatedUser(token);
            if (authenticatedUser.getRole().getName() == RoleNameEnum.User) {
                responseDTO.add(modelMapper.map(authenticatedUser, UserDTO.class));
            } else {
                Pageable pageable = PageRequest.of(page, size);
                Page<User> users = userRepository.findAll(pageable);
                users.forEach(user -> responseDTO.add(modelMapper.map(user, UserDTO.class)));
            }
            return responseDTO;
        } catch (Exception e) {
            LogUtils.catchThrowable(e);
            throw new InternalServerException(e.getMessage());
        }
    }

    @Override
    public UserDTO getUserByEmail(String email, String token) throws Exception {
        UserDTO responseDTO;
        User authenticatedUser = authService.getAuthenticatedUser(token);
        GuardClauses.guardAgainstUnauthorizedUserEmail(authenticatedUser, email);
        try {
            Optional<User> user = userRepository.findByEmail(email);
            GuardClauses.guardAgainstUserNotFound(user);
            responseDTO = modelMapper.map(user, UserDTO.class);
            return responseDTO;
        } catch (CustomException customException) {
            LogUtils.catchThrowable(customException);
            throw customException;
        } catch (Exception e) {
            LogUtils.catchThrowable(e);
            throw new InternalServerException(e.getMessage());
        }
    }
}
