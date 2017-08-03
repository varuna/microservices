package com.varunarl.hello.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.varunarl.hello.exception.HelloException;
import com.varunarl.hello.exception.ProductNotAvailableException;

@ControllerAdvice
public class RESTExceptionHandler {

    @ExceptionHandler(HelloException.class)
    public ResponseEntity<Response> helloExceptionHandler(Exception ex){
        Response res = new Response(ex.getMessage());
        return new ResponseEntity<Response>(res, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(ProductNotAvailableException.class)
    public ResponseEntity<Response> productNotFoundException(Exception ex){
        Response res = new Response(ex.getMessage());
        return new ResponseEntity<Response>(res , HttpStatus.NOT_FOUND);
        
    }
}
