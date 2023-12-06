package com.prevalentware.monitoringapp.controller;

import com.prevalentware.monitoringapp.dto.UserDTO;
import com.prevalentware.monitoringapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers(@Min(0) @RequestParam int page, @Min(0) @RequestParam int size, @RequestHeader("Authorization") String token) throws  Exception {
        return new ResponseEntity<>(userService.getAllUsers(page, size, token), HttpStatus.OK);
    }

    @GetMapping("/{email}")
    public ResponseEntity<UserDTO> getUserByEmail(@PathVariable String email, @RequestHeader("Authorization") String token) throws Exception {
        return new ResponseEntity<>(userService.getUserByEmail(email, token), HttpStatus.OK);
    }
}
