package net.entrofi.spring.etag.etagdemo;

public class MyResource {
    private String name;

    public MyResource(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
