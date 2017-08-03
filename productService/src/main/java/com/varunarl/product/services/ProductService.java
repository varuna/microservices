package com.varunarl.product.services;

import java.util.List;

import com.varunarl.product.exception.ProductDeleteException;
import com.varunarl.product.exception.ProductNotAvailableException;
import com.varunarl.product.exception.ProductSaveException;
import com.varunarl.product.repos.dynamodb.Product;

public interface ProductService {

    List<Product> findAllProducts();
    Product find(String productId);
    String save(Product product) throws ProductSaveException;
    boolean update(Product product) throws ProductNotAvailableException, ProductSaveException;
    boolean delete(String productId) throws ProductNotAvailableException, ProductDeleteException;

}
