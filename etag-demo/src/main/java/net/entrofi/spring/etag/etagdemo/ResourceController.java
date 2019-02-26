package net.entrofi.spring.etag.etagdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ResourceController {

    private static List<MyResource> resources = new ArrayList<>();

    private static final Logger LOGGER = LoggerFactory.getLogger(ResourceController.class);

    public ResourceController() {
        resources.add(new MyResource("Some Guy"));
        resources.add(new MyResource("Some other guy"));
        resources.add(new MyResource("some different guy"));
    }

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello Hasan";
    }

    @GetMapping("/resources")
    public List<MyResource> getResources() {
        LOGGER.info("Size of resources is: {}", resources.size());
        return resources;
    }

    @PutMapping("/resources/add")
    public void addResource(MyResource resource) {
        this.resources.add(resource);
        LOGGER.info("Added resources and the size of resources is: {}", resources.size());
    }
}
