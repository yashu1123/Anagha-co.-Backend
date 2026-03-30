package com.jobportal.backend.controller;

import com.jobportal.backend.entity.Product;
import com.jobportal.backend.repository.ProductRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@CrossOrigin("*")
public class ProductController {

    private final ProductRepository repository;

    public ProductController(ProductRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return repository.save(product);
    }
    @PostMapping("/bulk")
    public List<Product> createProducts(@RequestBody List<Product> products) {
        return repository.saveAll(products);
    }
}
