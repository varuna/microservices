package com.varunarl.product.services;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.varunarl.product.handlers.LoginResponse;

@Service
public class HelloWorldServiceImpl implements HelloWorldService {
    
    @Override
    @RequestMapping(value = "authenticate", method = RequestMethod.POST)
    public ResponseEntity<LoginResponse> authenticate(String username, String password){
        LoginResponse res = new LoginResponse("SUCCESS");
        res.setToken(UUID.randomUUID().toString());
        return new ResponseEntity<LoginResponse>(res, HttpStatus.OK);
    }
    
}
