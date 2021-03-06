package com.varunarl.auth.repos.dynamodb;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIgnore;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "User")
public class User implements UserDetails {

    /**
     * 
     */
    private static final long serialVersionUID = -7887150975772184176L;
    
    private String id;
    private String username;
    private String password;
    private String role;
    private boolean isEnabled;

    @DynamoDBHashKey
    @DynamoDBAutoGeneratedKey
    public String getId() {
        return id;
    }

    @DynamoDBAttribute
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @DynamoDBAttribute
    @Override
    public String getPassword() {
        return password;
    }

    @DynamoDBIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @DynamoDBIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @DynamoDBIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @DynamoDBAttribute
    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    @DynamoDBAttribute
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
    

}
