package com.varunarl.product.repos.dynamodb;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@EnableScan
public interface ProductRespository extends CrudRepository<Product, String>{
    List<Product> findById(String Id);
}
