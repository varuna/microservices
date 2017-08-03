package com.varunarl.product.services;

import org.springframework.http.ResponseEntity;

import com.varunarl.product.handlers.LoginResponse;

public interface HelloWorldService {
    
    public ResponseEntity<LoginResponse> authenticate(String username, String password);

}
