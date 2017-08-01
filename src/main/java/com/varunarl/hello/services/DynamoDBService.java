package com.varunarl.hello.services;

import java.util.List;

import com.varunarl.hello.repos.dynamodb.Product;
import com.varunarl.hello.repos.dynamodb.User;

public interface DynamoDBService {

    List<Product> findAllProducts();
    List<Product> findProductById(String id);
    List<User> findAllUsers(); 
//    Product find(long productId);

}
