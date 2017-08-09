package com.virtusa.nyl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
public class Application {

   
    private static Sender sender;
    
    @Autowired
    public Application(Sender sender) {
        this.sender = sender;
        // TODO Auto-generated constructor stub
    }
     
   
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);        
        sender.send("topic4", "first message");
    }    
    
    
    @RestController
    class Controller{
        
        @RequestMapping("/sendmessage")
        public void send(String message){
            sender.send("test", message);
        }
    }

}
