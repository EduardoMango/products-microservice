package com.EduardoMango.products.microservice.repositories;


import com.EduardoMango.products.microservice.model.documents.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {

    Page<Product> findAll(Pageable pageable);
}
