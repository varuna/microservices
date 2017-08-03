package com.varunarl.auth.exception;

import java.util.HashMap;
import java.util.Map;

public class HelloException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 1234L;
    private static Map<Integer,Map<Integer,String>> reasonCodes;

    static{
        reasonCodes = new HashMap<Integer, Map<Integer,String>>();
        Map<Integer,String> reasonCode1 = new HashMap<Integer, String>();
        reasonCode1.put(1, "Invalid username");
        reasonCode1.put(2, "Invalid password");
        
        Map<Integer,String> reasonCode2 = new HashMap<Integer, String>();
        reasonCode2.put(1, "Invalid application state");
        reasonCode2.put(2, "deliberate app crash");
        
        reasonCodes.put(1,reasonCode1);
        reasonCodes.put(2,reasonCode2);
    }
    
    
    public HelloException(int code, int reason) {
        super(reasonCodes.get(code).get(reason));
    }

    
}
