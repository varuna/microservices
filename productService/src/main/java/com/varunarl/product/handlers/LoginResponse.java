package com.varunarl.product.handlers;

public class LoginResponse extends Response {

    public String token;
    
    public LoginResponse(String responseMessage) {
        super(responseMessage);
    }

    public void setToken(String token){
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
