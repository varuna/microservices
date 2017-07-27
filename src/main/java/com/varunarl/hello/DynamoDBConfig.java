package com.varunarl.hello;

import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;

@Configuration
@EnableDynamoDBRepositories(basePackages = "com.varunarl.hello.repos")
public class DynamoDBConfig {

	@Value("${amazon.dynamodb.endpoint}")
	private String amazonDynamoDBEndpoint;
	
	@Value("${amazon.aws.accesskey}")
	private String amazonAWSAccessKey;
	
	@Value("${amazon.aws.secretkey}")
	private String amazonAWSSecretKey;
	
	@Bean
	public AmazonDynamoDB amazonDynmoDB(){
		AmazonDynamoDB db = new AmazonDynamoDBClient(awsCredentials());
		if (!StringUtils.isEmpty(amazonDynamoDBEndpoint)){
			db.setEndpoint(amazonDynamoDBEndpoint);
		}
		return db;
	}
	
	@Bean
	public AWSCredentials awsCredentials(){
		return new BasicAWSCredentials(amazonAWSAccessKey, amazonAWSSecretKey);
	}
}
 