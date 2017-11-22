# boot-oauth2

Small Project using Spring Boot OAuth2 and Okta as the IDP

Below are some links used in the making of this.
The Official Okta tutorials are in Groovy + I had some issues figuring exactly what values were used based on the metadata.
This is the first step on getting together an example which can pass the OAuth Token between services.

- https://developer.okta.com/blog/2017/03/21/spring-boot-oauth
- https://github.com/oktadeveloper/okta-spring-boot-oauth-example
- http://www.baeldung.com/spring-security-openid-connect
- https://spring.io/guides/tutorials/spring-boot-oauth2/


## Modules

#### boot-oauth2-common
Contains Common config and domain classes.
ClientID and Secret should be placed in the properties file in this project and are included in the others.

#### boot-oauth2-edge
This module is the MVC endpoint and acts as the oauth client.

The default ( / ) endpoint will display some details about the token (including the JWT Token)<br/>
The /service end point, points to the resource service running on localhost and will pass the token to this service for authentication.<br/>
The /unauthedservice points to the service with no Spring Security Enabled and returns the hard coded name of the service.

It runs on port 8080 and should run with the spring profile **fe**.

#### boot-oauth2-resource

A service application which is setup as an OAuth2 resource server. Expects the Token to be supplied and uses that to load the user's details from the Authentication Service.

It runs on port 8082 and should run with the spring profile **service**.


#### boot-oauth2-service-unauthed

A service application which doesn't include any security setup. Useful to check linking a service to the edge. 
It runs on port 8082 and should run with the spring profile **service**.

## Diagram
![alt text][logo]

[logo]: docs/BootOauth2.png "Basic layout"

### Next Steps
- Create a new Service that can act as both an OAuth Client and a Resource Service. This involves adding the both sets of filters to the HTTP Stack.
- Add the ability to run another resource service which is registered as a seperate application, but which can use the same credentials (and Tokens)