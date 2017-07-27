package com.varunarl.hello;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.varunarl.hello.repos.UserInfoRepository;


@RestController
public class HelloWorld {

	private static boolean init = false;
	
	private DynamoDBMapper dbMapper;
	
	@Autowired
	private AmazonDynamoDB amazonDynamoDB;
	
	@Autowired
	UserInfoRepository userRepo;
	
	private void init(){
		if (init){
			return;
		}
		dbMapper = new DynamoDBMapper(amazonDynamoDB);
		CreateTableRequest tableRequest = dbMapper.generateCreateTableRequest(User.class);
		tableRequest.setProvisionedThroughput(new ProvisionedThroughput(1L, 1L));
		amazonDynamoDB.createTable(tableRequest);
		init = true;
	}
	
	@RequestMapping("hello")
	public String hello(String name){
		init();
//		List<User> userList = (List<User>) userRepo.findAll();
		return "Hello "+name+". How are you doing today?";
	}
	
	@RequestMapping("findAll")
	public List<User> findAll(){
		init();
		return (List<User>) userRepo.findAll();
	}
}
