package com.varunarl.auth;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.varunarl.auth.repos.dynamodb.User;
import com.varunarl.auth.repos.dynamodb.UserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@ActiveProfiles("local")
public class UserRepositoryTest {

    private DynamoDBMapper dbMapper;

    @Autowired
    private AmazonDynamoDB amazonDynamoDB;

    @Autowired
    private UserRepository repository;

    @Before
    public void setup() {
        dbMapper = new DynamoDBMapper(amazonDynamoDB);
        if (!amazonDynamoDB.listTables().getTableNames().contains("User")) {
            CreateTableRequest req = dbMapper.generateCreateTableRequest(User.class);
            req.setProvisionedThroughput(new ProvisionedThroughput(10L, 10L));
            amazonDynamoDB.createTable(req);
        }

    }

    @Test
    public void addUserVarunaTest() {
        User varuna = new User();
        varuna.setUsername("Varuna");
        varuna.setPassword(new BCryptPasswordEncoder().encode("password1"));
        varuna.setEnabled(true);
        varuna = repository.save(varuna);
        assertFalse(varuna.getId().isEmpty());
        System.out.println(varuna.getPassword());
    }
    
    @Test
    public void deleteUserTest(){
        User varuna = repository.findOneByUsername("Varuna");
        User sujeewa = repository.findOneByUsername("Sujeewa");
        assertNotNull(varuna);
        assertNotNull(sujeewa);
        repository.delete(varuna.getId());
        repository.delete(sujeewa.getId());
        varuna = repository.findOneByUsername("Varuna");
        sujeewa = repository.findOneByUsername("Sujeewa");
        assertNull(varuna);
        assertNull(sujeewa);
    }

    @Test
    public void addUserSujeewaTest() {
        User sujeewa = new User();
        sujeewa.setUsername("Sujeewa");
        sujeewa.setPassword(new BCryptPasswordEncoder().encode("password1"));
        sujeewa.setEnabled(true);
        sujeewa = repository.save(sujeewa);
        assertFalse(sujeewa.getId().isEmpty());
        System.out.println(sujeewa.getPassword());
    }

}
