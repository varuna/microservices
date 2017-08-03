package com.varunarl.hello.services;

import java.util.List;

import com.varunarl.hello.exception.ProductNotAvailableException;
import com.varunarl.hello.repos.dynamodb.Product;

public interface ProductService {

    List<Product> findAllProducts();
    Product find(String productId);
    String save(Product product);
    boolean update(Product product) throws ProductNotAvailableException;
    boolean delete(String productId);

}
