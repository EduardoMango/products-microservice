package com.EduardoMango.products.microservice.services;

import com.EduardoMango.products.microservice.model.ProductEntity;
import com.EduardoMango.products.microservice.repositories.ProductRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PostConstruct
    public void init() {
        productRepository.findAll().forEach(System.out::println);
    }

    public ProductEntity createProduct(ProductEntity product) {
        return productRepository.save(product);
    }
}
