package com.varunarl.hello.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.varunarl.hello.handlers.HelloException;
import com.varunarl.hello.handlers.Response;

@RestController
public class HelloWorldController {

    @RequestMapping("hello")
    public ResponseEntity<Response> hello(String name) throws Exception {
        String role = "User";
        if (name.isEmpty()){
            throw new HelloException(1,1);
        }else if ("Varuna".equals(name)){
            role = "admin";
        }else if ("Sujeewa".equals(name))
        {
            role = "Product Owner";
        }else if ("crash".equals(name)){
            throw new HelloException(2,2);
        }
        return new ResponseEntity<Response>(new Response("Hello " + name + ". You've been assigned with role "+role), HttpStatus.OK);
    }


}
