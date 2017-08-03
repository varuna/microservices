package com.varunarl.hello;


import static  org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.varunarl.hello.handlers.LoginResponse;
import com.varunarl.hello.services.HelloWorldService;
import com.varunarl.hello.services.HelloWorldServiceImpl;

@RunWith(SpringRunner.class)
public class HelloWorldServiceTest {

    @Autowired
    private HelloWorldService helloWorldService;

    @TestConfiguration
    static class HelloWorldServiceTestContextConfiguration{
        
        @Bean
        public HelloWorldService helloWorldService(){
            return new HelloWorldServiceImpl();
        }
    }

    @Test
    public void testAuthenticate() throws Exception{
        ResponseEntity<LoginResponse> loginRes = helloWorldService.authenticate("Varuna", "sample");
        assertTrue(loginRes != null);
        assertFalse(loginRes.getBody().getToken().isEmpty());
   }
}
