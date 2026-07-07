package com.cognizant.springlearn.controller;

import com.cognizant.springlearn.security.JwtUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@RestController
public class AuthenticationController {
    private final JwtUtil jwtUtil;

    public AuthenticationController(JwtUtil jwtUtil) { this.jwtUtil = jwtUtil; }

    @GetMapping("/authenticate")
    public ResponseEntity<Map<String, String>> authenticate(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization) {
        String username = getUser(authorization);
        Map<String, String> token = new HashMap<>();
        token.put("token", jwtUtil.generateToken(username));
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    private String getUser(String authorization) {
        String base64Credentials = authorization.substring("Basic ".length());
        String credentials = new String(Base64.getDecoder().decode(base64Credentials), StandardCharsets.UTF_8);
        return credentials.substring(0, credentials.indexOf(':'));
    }
}
