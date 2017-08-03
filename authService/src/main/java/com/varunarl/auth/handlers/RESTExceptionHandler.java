package com.varunarl.auth.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.varunarl.auth.exception.HelloException;
import com.varunarl.auth.exception.ProductNotAvailableException;
import com.varunarl.auth.exception.ProductSaveException;

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
    
    @ExceptionHandler(ProductSaveException.class)
    public ResponseEntity<Response> productSaveException(Exception ex){
        Response res = new Response(ex.getMessage());
        return new ResponseEntity<Response>(res , HttpStatus.INTERNAL_SERVER_ERROR);
        
    }
}
