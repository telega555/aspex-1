package com.example.aspex1.controller;

import com.example.aspex1.payload.JwtAuthResponse;
import com.example.aspex1.payload.LoginDto;
import com.example.aspex1.payload.RegisterDto;
import com.example.aspex1.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor

public class AuthController {
    private final AuthService authService;

    @PostMapping(value = {"/register", "/signup"})
    public  ResponseEntity<JwtAuthResponse> register(@RequestBody RegisterDto registerDto) {
        String token = authService.register(registerDto);
        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
        jwtAuthResponse.setAccessToken(token);
        System.out.println(token);
        return ResponseEntity.ok(jwtAuthResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> login(@RequestBody LoginDto loginDto){
        String token = authService.login(loginDto);
        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
        jwtAuthResponse.setAccessToken(token);
        return ResponseEntity.ok(jwtAuthResponse);
    }
}
