package com.EduardoMango.products.microservice.model.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.math.BigDecimal;
import java.util.List;

public class ProductRequest {

    @NotEmpty
    private String name;
    @NotEmpty
    private String description;
    private String mainImageUrl;
    private List<String> imageUrls;

    @Field(targetType = FieldType.DECIMAL128)
    @PositiveOrZero
    private BigDecimal price;

    @Min(0)
    @Max(5)
    private Double rating;

    @PositiveOrZero
    private Long unitsSold;
    @PositiveOrZero
    private Long stock;
    private Boolean active;

    @Field(targetType = FieldType.DECIMAL128)
    private BigDecimal discount;

    @NotEmpty
    private String sellerId;
}
