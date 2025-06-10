package com.EduardoMango.products.microservice.controllers;

import com.EduardoMango.products.microservice.model.documents.Product;
import com.EduardoMango.products.microservice.services.ProductService;
import org.springframework.data.domain.PageRequest;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public ResponseEntity<PagedModel<EntityModel<Product>>> getAll(@RequestParam(required = false, defaultValue = "0") int page,
                                                                   @RequestParam(required = false, defaultValue = "25") int size,
                                                                   @RequestParam(required = false) String category,
                                                                   @RequestParam(required = false) String minPrice,
                                                                   @RequestParam(required = false) String maxPrice,
                                                                   @RequestParam(required = false) Boolean onSale){

        HashSet<String> strings = new HashSet<>();
        strings.f`
        return ResponseEntity.ok(productService.getProducts(PageRequest.of(page,size), category, minPrice, maxPrice, onSale));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Product>> getById(@PathVariable String id){
        return ResponseEntity.ok(productService.getProductById(id));
    }
}
