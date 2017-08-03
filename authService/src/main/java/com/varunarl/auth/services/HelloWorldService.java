package com.varunarl.auth.services;

import org.springframework.http.ResponseEntity;

import com.varunarl.auth.handlers.LoginResponse;

public interface HelloWorldService {
    
    public ResponseEntity<LoginResponse> authenticate(String username, String password);

}
