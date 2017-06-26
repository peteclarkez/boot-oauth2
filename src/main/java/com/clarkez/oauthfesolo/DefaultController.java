package com.clarkez.oauthfesolo;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Map;

@RestController
public class DefaultController {

    @RequestMapping("/")
    public String sayHello(Principal principal){
        String user = "ME";
        if(principal!=null){
            user = principal.getName();
            //String nick = ((Map<String,String>)(((Authentication)principal).getDetails())).get("NickName");
        }

        return "Hello "+user;
    }
}
