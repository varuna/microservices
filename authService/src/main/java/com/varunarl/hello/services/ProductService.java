package com.varunarl.hello.services;

import java.util.List;

import com.varunarl.hello.exception.ProductDeleteException;
import com.varunarl.hello.exception.ProductNotAvailableException;
import com.varunarl.hello.exception.ProductSaveException;
import com.varunarl.hello.repos.dynamodb.Product;

public interface ProductService {

    List<Product> findAllProducts();
    Product find(String productId);
    String save(Product product) throws ProductSaveException;
    boolean update(Product product) throws ProductNotAvailableException, ProductSaveException;
    boolean delete(String productId) throws ProductNotAvailableException, ProductDeleteException;

}
