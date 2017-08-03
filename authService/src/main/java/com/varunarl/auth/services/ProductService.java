package com.varunarl.auth.services;

import java.util.List;

import com.varunarl.auth.exception.ProductDeleteException;
import com.varunarl.auth.exception.ProductNotAvailableException;
import com.varunarl.auth.exception.ProductSaveException;
import com.varunarl.auth.repos.dynamodb.Product;

public interface ProductService {

    List<Product> findAllProducts();
    Product find(String productId);
    String save(Product product) throws ProductSaveException;
    boolean update(Product product) throws ProductNotAvailableException, ProductSaveException;
    boolean delete(String productId) throws ProductNotAvailableException, ProductDeleteException;

}