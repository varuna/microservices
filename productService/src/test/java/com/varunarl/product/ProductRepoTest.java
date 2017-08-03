package com.varunarl.product;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.DeleteTableRequest;
import com.amazonaws.services.dynamodbv2.model.ListTablesResult;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.varunarl.product.Application;
import com.varunarl.product.repos.dynamodb.Product;
import com.varunarl.product.repos.dynamodb.ProductRespository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@ActiveProfiles("local")
@WebAppConfiguration
public class ProductRepoTest {

    private DynamoDBMapper dbMapper;

    @Autowired
    private AmazonDynamoDB db;

    @Autowired
    private ProductRespository repository;

    @Before
    public void setup() {
        dbMapper = new DynamoDBMapper(db);
        ListTablesResult tableList = db.listTables();
        if (!tableList.getTableNames().contains("Product")) {
            CreateTableRequest cRequestProductTable = dbMapper.generateCreateTableRequest(Product.class);
            cRequestProductTable.setProvisionedThroughput(new ProvisionedThroughput(10L, 10L));

            db.createTable(cRequestProductTable);
        }

        dbMapper.batchDelete((List<Product>) repository.findAll());
    }

    @Test
    public void testProductTable() {
        Product spoon = new Product();
        spoon.setProductName("Spoon");
        spoon.setCost(50.0);
        spoon.setPrice(55.50);

        repository.save(spoon);

        List<Product> result = (List<Product>) repository.findAll();

        assertTrue("Not empty", result.size() > 0);
    }

    @After
    public void after() {
        DeleteTableRequest dRequestProductTable = dbMapper.generateDeleteTableRequest(Product.class);
        db.deleteTable(dRequestProductTable);
    }

    public void testUserTable() {

    }
}
