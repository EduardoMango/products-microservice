package com.EduardoMango.products.microservice.services;


import com.EduardoMango.products.microservice.exceptions.ProductNotFoundException;
import com.EduardoMango.products.microservice.model.documents.Product;
import com.EduardoMango.products.microservice.hateoas.ProductModelAssembler;
import com.EduardoMango.products.microservice.repositories.ProductRepository;
import com.EduardoMango.products.microservice.util.ConverterUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.math.BigDecimal;
import org.springframework.data.mongodb.core.query.Criteria;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductFilteringService productFilteringService;
    private final ProductModelAssembler productModelAssembler;
    private final PagedResourcesAssembler<Product> pagedResourcesAssembler;
    private final MongoTemplate mongoTemplate;

    public ProductService(ProductRepository productRepository, ProductFilteringService productFilteringService,
                          ProductModelAssembler productModelAssembler,
                          PagedResourcesAssembler<Product> pagedResourcesAssembler, MongoTemplate mongoTemplate) {
        this.productRepository = productRepository;
        this.productFilteringService = productFilteringService;
        this.productModelAssembler = productModelAssembler;
        this.pagedResourcesAssembler = pagedResourcesAssembler;
        this.mongoTemplate = mongoTemplate;
    }

    public PagedModel<EntityModel<Product>> getProducts(Pageable pageable,
                                                        String categoria,
                                                        String precioMin,
                                                        String precioMax,
                                                        Boolean enOferta){


        Query baseQuery = productFilteringService.buildFilterQuery(categoria,
                ConverterUtil.stringToBigDecimal(precioMin),
                ConverterUtil.stringToBigDecimal(precioMax),
                enOferta);

        Query paginatedQuery = baseQuery.with(pageable);
        List<Product> products = mongoTemplate.find(paginatedQuery, Product.class);
        long total = mongoTemplate.count(baseQuery, Product.class);

        Page<Product> page = new PageImpl<>(products, pageable, total);

        return pagedResourcesAssembler.toModel(page, productModelAssembler);
    }

    public EntityModel<Product> getProductById(String id){
        return productModelAssembler.toModel(productRepository.findById(id).orElseThrow(()-> new ProductNotFoundException(id)));
    }

    public void deleteProductById(String id){
        productRepository.deleteById(id);
    }

    public void saveProduct(Product product){
        productRepository.save(product);
    }

}