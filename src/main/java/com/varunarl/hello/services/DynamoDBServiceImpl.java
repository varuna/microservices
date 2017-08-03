package com.varunarl.hello.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.varunarl.hello.exception.ProductNotAvailableException;
import com.varunarl.hello.repos.dynamodb.Product;
import com.varunarl.hello.repos.dynamodb.ProductRespository;
import com.varunarl.hello.repos.dynamodb.User;
import com.varunarl.hello.repos.dynamodb.UserRepository;

@Service("DynamoDBService")
public class DynamoDBServiceImpl implements ProductService {

    @Autowired
    private ProductRespository productRepository;
    
    @Override
    public List<Product> findAllProducts() {
        return (List<Product>) productRepository.findAll();
    }

    @Override
    public Product find(String productId) {
        return productRepository.findOne(productId);
    }

    @Override
    public String save(Product product) {
        return productRepository.save(product).getId();
    }

    @Override
    public boolean update(Product product) throws ProductNotAvailableException {
        boolean isExiting = productRepository.exists(product.getId());
        if (!isExiting){
            throw new ProductNotAvailableException(product.getId());
        }
        return false;
    }

    @Override
    public boolean delete(String productId) {
        // TODO Auto-generated method stub
        return false;
    }
    

}
