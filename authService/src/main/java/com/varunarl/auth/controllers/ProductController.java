package com.varunarl.auth.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.varunarl.auth.exception.ProductNotAvailableException;
import com.varunarl.auth.exception.ProductSaveException;
import com.varunarl.auth.handlers.Response;
import com.varunarl.auth.repos.dynamodb.Product;
import com.varunarl.auth.services.ProductService;

@RestController
public class ProductController {

    @Autowired
    private ProductService dbService;

    @RequestMapping(value = "product/", method = RequestMethod.GET)
    public ResponseEntity<List<Product>> findAll() {
        List<Product> allProducts = dbService.findAllProducts();
        if (allProducts.isEmpty()) {
            return new ResponseEntity<List<Product>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Product>>(allProducts, HttpStatus.OK);
    }

    @RequestMapping(value = "product/{id}", method = RequestMethod.GET)
    public ResponseEntity<Product> findOne(@PathVariable("id") String productId) {
        Product p = dbService.find(productId);
        if (p == null) {
            return new ResponseEntity<Product>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Product>(p, HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "product/", method = RequestMethod.PUT)
    public ResponseEntity<Response> save(@RequestBody Product product) throws ProductSaveException {
        String id = dbService.save(product);
        Response response = new Response("Product " + id + " is created.");
        return new ResponseEntity<Response>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "product/", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Response> update(Product product) throws ProductNotAvailableException, ProductSaveException {
        boolean isUpdateSuccessful = dbService.update(product);
        if (isUpdateSuccessful) {
            Response response = new Response("Product " + product.getId() + " is updated.");
            return new ResponseEntity<Response>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Response>(new Response("Cannot update " + product.getId()), HttpStatus.NOT_MODIFIED);
    }

}
