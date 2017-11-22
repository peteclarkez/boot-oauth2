package com.clarkez.oauthfesolo;

import com.clarkez.common.domain.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoRestTemplateFactory;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.security.Principal;

@RestController
public class DefaultController {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    OAuth2ClientContext oauth2ClientContext;

    @Autowired
    UserInfoTokenServices userInfoTokenServices;

    @Autowired
    UserInfoRestTemplateFactory userInfoRestTemplateFactory;

    @RequestMapping("/")
    public String sayHello(Principal principal, Authentication auth){

        OAuth2AccessToken token = oauth2ClientContext.getAccessToken();
        OAuth2RefreshToken rtoken = token.getRefreshToken();
        OAuth2RestTemplate restT = userInfoRestTemplateFactory.getUserInfoRestTemplate();

        String user = principal.getName();

        StringBuilder sb = new StringBuilder();
        sb.append("<html><head><title>Welcome In</title></head><body>");
        sb.append("<p>Welcome to this Example Controller, <b>"+user+"</b></p>");
        sb.append("<p>Token Type: <b>"+token.getTokenType()+"</b></p>");
        sb.append("<p>Token Scope: <b>"+token.getScope() +"</b></p>");
        sb.append("<p>Token Expiration: <b>"+ token.getExpiration()+"</b></p>");
        sb.append("<p>Token Value: <b>"+ token.getValue()+"</b></p>");
        sb.append("<p></p>");

        sb.append("<p>Refresh Token: <b>"+rtoken.getValue()+ "</b></p>");

        sb.append("</body></html>");
        return sb.toString();

    }

    @RequestMapping("/unauthedservice")
    public String sayHelloFromUnauthedService(Principal principal, Authentication auth){

        ServiceResponse response = restTemplate.getForObject("http://localhost:8082/service", ServiceResponse.class);

        String user = "NOTME";
        if(response!=null){
            user = response.getUser();
        }

        return "Hello From, "+user;
    }

    @RequestMapping("/service")
    public String sayHelloFromService(Principal principal, Authentication auth){

        OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails)((OAuth2Authentication) principal).getDetails();
        String tokenValue = details.getTokenValue();

        OAuth2ProtectedResourceDetails resource = null;

        OAuth2AccessToken token = oauth2ClientContext.getAccessToken();
        OAuth2RefreshToken rtoken = token.getRefreshToken();
        OAuth2RestTemplate ouathRestTemplate = userInfoRestTemplateFactory.getUserInfoRestTemplate();


        //ServiceResponse response = restTemplate.getForObject("http://localhost:8081/default", ServiceResponse.class);
        ServiceResponse response = ouathRestTemplate.getForObject("http://localhost:8085/default", ServiceResponse.class);


        String user = "NOTME";
        if(response!=null){
            user = response.getUser();
        }

        return "Hello To you, "+user;
    }
}
