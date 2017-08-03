package com.varunarl.product.handlers;

public class Response {
    private String responseMessage;

    public Response(String responseMessage) {
        this.responseMessage = responseMessage;
    }


    public String getMessage() {
        return responseMessage;
    }
}
