package com.varunarl.hello.controllers;

import java.util.List;

import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.varunarl.hello.exception.HelloException;
import com.varunarl.hello.handlers.Response;

@Produces("application/json")
@RestController
public class HelloWorldController {

    @Autowired
    private DiscoveryClient discoveryClient;
    
    @RequestMapping("hello")
    public ResponseEntity<Response> hello(String name) throws Exception {
        String role = "User";
        if (name != null && name.isEmpty()){
            throw new HelloException(1,1);
        }else if ("Varuna".equals(name)){
            role = "admin";
        }else if ("Sujeewa".equals(name))
        {
            role = "Product Owner";
        }else if ("crash".equals(name)){
            throw new HelloException(2,2);
        }
        return new ResponseEntity<Response>(new Response("Hello " + name + ". You've been assigned with role "+role), HttpStatus.OK);
    }
    
    @RequestMapping("service-instances")
    @Produces("application/json")
    public List<ServiceInstance> serviceInstanceByApplicationName(){
        return  this.discoveryClient.getInstances("helloApp");
    }


}
