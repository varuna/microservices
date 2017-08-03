package com.varunarl.hello.exception;

public class ProductNotAvailableException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 4722011775922286239L;
    
    private String productId;

    public ProductNotAvailableException(String productId) {
        super();
        this.productId = productId;
    }

    @Override
    public String getMessage() {
        return "Unable to find product with productId "+productId;
    }
    
    

}
