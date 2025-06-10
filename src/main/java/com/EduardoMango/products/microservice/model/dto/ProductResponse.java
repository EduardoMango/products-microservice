package com.EduardoMango.products.microservice.model.dto;


import jakarta.validation.constraints.*;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.math.BigDecimal;
import java.util.List;

public class ProductResponse {

    private String id;
    private String name;
    private String description;
    private String mainImageUrl;
    private List<String> imageUrls;
    private BigDecimal price;
    private Double rating;
    private Long unitsSold;
    private Long stock;
    private Boolean active;
    private BigDecimal discount;
    private String sellerId;
}
