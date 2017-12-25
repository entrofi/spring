# Simple Json Web Token (JWT) Authentication with Spring Boot
This example demonstrates basic concepts of using JWT based authentication using Spring. Application is backed by spring-data-jpa. Therefore, in order to see a demo, it is necessary to create a user using ```"<HOST>/signup"``` endpoint.
The files to follow implementation details and some important checkpoints are listed below: 
-  The requests which we want to be coming from authenticated clients should be intercepted and the incoming Json Web Token must be validated. To do this, the demo contains a servlet filter named``` net.entrofi.spring.security.jwt.simplejwtauth.security.SimpleJWTAuthenticationFilter``` which is an extension of ```BasicAuthenticationFilter``` provided in  spring security module. This filter gets JWT from corresponding header, validates it using jwt parser. A valid JWT should: 
  -  be well-formed, 
  -  have a valid signature, 
  -  have valid claims
 
 Here in this filter we validate incoming JWT and create an UsernamePasswordAuthenticationToken and pass it to security context. 
-   Authentication phase is handled by another filter added to application which is an extension of ```UsernamePasswordAuthenticationFilter``` provided in spring security package. ```SimpleJWTAuthenticationFilter``` which uses authenticationManager in context to authenticate the user described by JWT. 
- Our actual check if the user is a valid one to our system is checked in UserDetailsService  implementation as usual. Check the implementation ```UserDetailsServiceImpl```  for details. 

