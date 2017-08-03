package com.varunarl.hello.services;

import org.springframework.http.ResponseEntity;

import com.varunarl.hello.handlers.LoginResponse;

public interface HelloWorldService {
    
    public ResponseEntity<LoginResponse> authenticate(String username, String password);

}
