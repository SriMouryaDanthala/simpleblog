package com.srimourya.simpleblog.controllers;

import com.srimourya.simpleblog.dto.RegistrationResponseDTO;
import com.srimourya.simpleblog.dto.UserLoginDTO;
import com.srimourya.simpleblog.dto.UserRegistrationDTO;
import com.srimourya.simpleblog.models.ApiResponse;
import com.srimourya.simpleblog.models.ServiceResponse;
import com.srimourya.simpleblog.repo.UserDetailsRepo;
import com.srimourya.simpleblog.service.UserLoginService;
import com.srimourya.simpleblog.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class UserController {
    @Autowired
    private UserDetailsRepo userDetailsRepo;

    @Autowired
    private UserLoginService loginService;
    @Autowired
    private UsersService usersService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> register(@RequestBody  UserRegistrationDTO userRegistrationDTO) {
        ServiceResponse response =  loginService.register(userRegistrationDTO);
        return  ResponseEntity.status(response.getHttpStatus()).body(ApiResponse.importAPIResponseFromService(response));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@RequestBody UserLoginDTO userLoginDTO) {
        ServiceResponse response = loginService.verify(userLoginDTO);
        return ResponseEntity.status(response.getHttpStatus()).body(ApiResponse.importAPIResponseFromService(response));
    }

    @GetMapping("/user/{userID}")
    public ResponseEntity<ApiResponse> getUser(@PathVariable UUID userID) {
        ServiceResponse response =  usersService.getUserProfile(userID);
        return ResponseEntity.status(response.getHttpStatus()).body(ApiResponse.importAPIResponseFromService(response));
    }

    @PutMapping("/user")
    public ResponseEntity<ApiResponse> updateUser(@RequestBody RegistrationResponseDTO registrationResponseDTO) {
        ServiceResponse response  = usersService.updateUser(registrationResponseDTO);
        return ResponseEntity.status(response.getHttpStatus()).body(ApiResponse.importAPIResponseFromService(response));
    }
}
