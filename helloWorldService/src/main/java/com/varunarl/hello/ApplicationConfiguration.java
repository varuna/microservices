package com.varunarl.hello;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.util.FileCopyUtils;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationConfiguration extends ResourceServerConfigurerAdapter {

    private static final String RESOURCE_ID = "helloapp_resources";

    @Autowired
    private TokenStore tokenStore;
    
    @Autowired
    private JwtAccessTokenConverter jwtAccessTokenConverter;
    
    @Bean
    @Qualifier("tokenStore")
    public TokenStore tokenStore(){
        return new JwtTokenStore(jwtAccessTokenConverter);
    }
    
    @Bean
    protected JwtAccessTokenConverter jwtTokenEnhancer() {
        JwtAccessTokenConverter converter =  new JwtAccessTokenConverter();
        Resource resource = new ClassPathResource("pubcert.cert");
        String publicKey = null;
        try {
            publicKey = new String(FileCopyUtils.copyToByteArray(resource.getInputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        converter.setVerifierKey(publicKey);
        return converter;
    }
    
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.tokenStore(tokenStore);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
            .anonymous().disable()
            .csrf().disable()
            .authorizeRequests()
            .antMatchers("/**").authenticated()
            .antMatchers(HttpMethod.GET, "/hello").hasAuthority("HELLO_READ")
            .and().exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
    }

}
