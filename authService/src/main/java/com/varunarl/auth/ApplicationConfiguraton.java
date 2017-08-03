package com.varunarl.auth;

import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.util.StringUtils;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;

@Configuration
@EnableDynamoDBRepositories(basePackages = "com.varunarl.auth.repos.dynamodb")
public class ApplicationConfiguraton extends AuthorizationServerConfigurerAdapter{
    
    @Value("${amazon.dynamodb.endpoint}")
    private String amazonDynamoDBEndpoint;
    
    @Value("${amazon.aws.accesskey}")
    private String amazonAWSAccessKey;
    
    @Value("${amazon.aws.secretkey}")
    private String amazonAWSSecretKey;
    
    @Autowired
    @Qualifier("userDetailsService")
    private UserDetailsService userDetailsService;
    
    @Value("${services.auth.tokenTimeout:3600}")
    private int expiry;
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Bean
    public AWSCredentials amazonAWSCredentials(){
        return new BasicAWSCredentials(amazonAWSAccessKey, amazonAWSSecretKey);
    }
    
    @Bean
    public AmazonDynamoDB amazonDynamoDB(){
        AmazonDynamoDB db = new AmazonDynamoDBClient(amazonAWSCredentials());
        if (!StringUtils.isEmpty(amazonDynamoDBEndpoint))
            db.setEndpoint(amazonDynamoDBEndpoint);
        return db;
    }
    
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory().withClient("microservice").secret("oursecret").accessTokenValiditySeconds(expiry)
        .scopes("read","write").authorizedGrantTypes("password","refresh_token").resourceIds("resource");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer configurer) throws Exception {
        configurer.authenticationManager(authenticationManager);
        configurer.userDetailsService(userDetailsService);
    }
    
    

}
