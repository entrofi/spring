package net.entrofi.spring.springbootdatajpa.controller;

import net.entrofi.spring.springbootdatajpa.data.entity.Product;
import net.entrofi.spring.springbootdatajpa.data.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

    private ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/products")
    List<Product> getProducts() {
        return productRepository.findAll();
    }
}
