package com.clarkez.unauthedService;

import com.clarkez.common.domain.ServiceResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class DefaultController {

    @RequestMapping("/service")
    public ServiceResponse sayHello(){

        String servicename = "SERVICE_A";
        ServiceResponse sr = new ServiceResponse(servicename,"Hello From service "+ servicename + " from Authenticated Service");

        return sr;
    }
}
