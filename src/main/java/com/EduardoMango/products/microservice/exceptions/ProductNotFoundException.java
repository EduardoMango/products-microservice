package com.EduardoMango.products.microservice.exceptions;


import lombok.Getter;

import java.util.NoSuchElementException;

@Getter
public class ProductNotFoundException extends NoSuchElementException {

    private final String id;

    public ProductNotFoundException(String id) {
        this.id = id;
    }
}
