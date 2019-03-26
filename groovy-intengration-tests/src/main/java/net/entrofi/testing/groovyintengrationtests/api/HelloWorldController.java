package net.entrofi.testing.groovyintengrationtests.api;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {


    @GetMapping("/hello")
    public String sayHello(@RequestParam(name = "name") String name) {
        return "Hello " + name;
    }
}
