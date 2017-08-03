package com.varunarl.auth.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.varunarl.auth.handlers.Response;
import com.varunarl.auth.repos.dynamodb.User;
import com.varunarl.auth.repos.dynamodb.UserRepository;

@RestController
public class UserController {

    @Autowired
    private UserRepository repository;

    @RequestMapping(value = "/user/", method = RequestMethod.GET)
    public ResponseEntity<Iterable<User>> findAll() {
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/user/", method = RequestMethod.PUT)
    public ResponseEntity<Response> addUser(User user) {
        User createdUsed = repository.save(user);
        String message = "User " + createdUsed.getId() + " is created";
        return new ResponseEntity<Response>(new Response(message), HttpStatus.OK);
    }
}
