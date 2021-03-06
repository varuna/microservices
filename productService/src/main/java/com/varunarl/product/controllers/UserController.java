package com.varunarl.product.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.varunarl.product.repos.dynamodb.User;
import com.varunarl.product.repos.dynamodb.UserRepository;

@RestController
public class UserController {

    @Autowired
    private UserRepository repository;
    
    @RequestMapping(value="/user/", method=RequestMethod.GET)
    public ResponseEntity<Iterable<User>> findAll(){
        return new ResponseEntity<>(repository.findAll(),HttpStatus.OK);
    }
}
