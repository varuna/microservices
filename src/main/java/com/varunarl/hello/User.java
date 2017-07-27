package com.varunarl.hello;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "User")
public class User {
	
	private Integer id;
	private String firstName;
	private String lastName;
	private String contact;
	
	public User() {
	}	
	
	

	public User(Integer id, String firstName, String lastName, String contact) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.contact = contact;
	}



	public User(String firstName, String lastName, String contact) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.contact = contact;
	}

	@DynamoDBHashKey
	@DynamoDBAutoGeneratedKey
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	@DynamoDBAttribute
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@DynamoDBAttribute
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@DynamoDBAttribute
	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

//	public String getName() {
//		return this.getFirstName() + " " + this.getLastName();
//	}

}
