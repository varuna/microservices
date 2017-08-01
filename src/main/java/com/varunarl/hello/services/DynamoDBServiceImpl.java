package com.varunarl.hello.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.varunarl.hello.repos.dynamodb.Product;
import com.varunarl.hello.repos.dynamodb.ProductRespository;
import com.varunarl.hello.repos.dynamodb.User;
import com.varunarl.hello.repos.dynamodb.UserRepository;

@Service("DynamoDBService")
public class DynamoDBServiceImpl implements DynamoDBService {

    @Autowired
    private ProductRespository productRepository;
    
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Product> findAllProducts() {
        return (List<Product>) productRepository.findAll();
    }
    
    @Override
    public List<Product> findProductById(String id){
        return productRepository.findById(id);
    }

    @Override
    public List<User> findAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    
    

}
