package com.varunarl.hello.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.varunarl.hello.repos.dynamodb.Product;
import com.varunarl.hello.services.DynamoDBService;

@RestController
public class ProductController {
    
    @Autowired
    private DynamoDBService dbService;

    @RequestMapping(value="product/", method=RequestMethod.GET)
    public ResponseEntity<List<Product>> product(){
        List<Product> allProducts = dbService.findAllProducts();
        if (allProducts.isEmpty()){
            return new ResponseEntity<List<Product>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Product>>(allProducts, HttpStatus.OK);
    }
    
//    @RequestMapping(value="product/{id}", method=RequestMethod.GET)
//    public ResponseEntity<Product> product(@PathVariable("id") long productId){
//        Product p = dbService.find(productId);
//        if ( p == null){
//            return new ResponseEntity<Product>(HttpStatus.NO_CONTENT);
//        }
//        return new ResponseEntity<Product>(p, HttpStatus.NO_CONTENT);
//    }
    
}
