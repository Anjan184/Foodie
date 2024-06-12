package com.foodie.Config;

import com.foodie.controllers.AuthController;
import com.foodie.models.JwtService;
import com.foodie.security.JwtHelper;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.client.RestTemplate;

@Configuration
public class FeignClientConfiguration {


    @Value("${jwt.header}")
    private String jwtHeader;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private JwtService jwtService;

    @Bean
    public RequestInterceptor requestInterceptor(){
        return new RequestInterceptor() {
            @Override
            public void apply(RequestTemplate requestTemplate) {
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                if(authentication != null && authentication.isAuthenticated()){
//                    String jwtToken = restTemplate.getForObject("http://localhost/8080/auth/login",String.class);

                    String token= jwtService.getJwtToken();
                    System.err.println(token);
                    requestTemplate.header(jwtHeader, token);
                }
            }
        };
    }
}
