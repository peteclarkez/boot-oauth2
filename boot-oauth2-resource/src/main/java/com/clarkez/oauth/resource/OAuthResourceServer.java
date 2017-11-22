package com.clarkez.oauth.resource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.Map;

@SpringBootApplication
@RestController
@EnableResourceServer
public class OAuthResourceServer {

    public static void main(String[] args) {
        SpringApplication.run(OAuthResourceServer.class, args);
    }


    @RequestMapping("/default")
    public Map<String,String> sayHello(Principal principal){
        Map<String,String> response = new LinkedHashMap<>();
        String user = "UNKNOWN";

        if(principal!=null){
            user = principal.getName();
        }

        response.put("user",user);
        response.put("msg","Hello From user "+user + " from Authenticated Service");

        return response;
    }
}
