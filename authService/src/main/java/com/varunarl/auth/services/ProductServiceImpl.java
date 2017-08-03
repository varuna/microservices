package com.varunarl.auth.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.varunarl.auth.exception.ProductDeleteException;
import com.varunarl.auth.exception.ProductNotAvailableException;
import com.varunarl.auth.exception.ProductSaveException;
import com.varunarl.auth.repos.dynamodb.Product;
import com.varunarl.auth.repos.dynamodb.ProductRespository;

@Service("DynamoDBService")
public class ProductServiceImpl implements ProductService {

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
    public String save(Product product) throws ProductSaveException {
        try {
            return productRepository.save(product).getId();
        } catch (Exception e) {
            throw new ProductSaveException(e.getMessage());
        }
    }

    @Override
    public boolean update(Product product) throws ProductNotAvailableException, ProductSaveException {
        if (product.getId() == null) {
            throw new ProductNotAvailableException(null);
        }
        boolean isExiting = productRepository.exists(product.getId());
        if (!isExiting) {
            throw new ProductNotAvailableException(product.getId());
        }
        try{
        productRepository.save(product);
        }catch (Exception e) {
            throw new ProductSaveException(e.getMessage());
        }
        return true;
    }

    @Override
    public boolean delete(String productId) throws ProductNotAvailableException, ProductDeleteException {
        if (productId == null) {
            throw new ProductNotAvailableException(null);
        }
        boolean isExiting = productRepository.exists(productId);
        if (!isExiting) {
            throw new ProductNotAvailableException(productId);
        }
        try {
            productRepository.delete(productId);
        } catch (Exception ex) {
            throw new ProductDeleteException(ex.getMessage());
        }
        return true;
    }

}
