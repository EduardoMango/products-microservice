package com.EduardoMango.products.microservice.model.documents;

import com.EduardoMango.products.microservice.model.enums.ProductStatus;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Document(collection = "products")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {

    @Id
    private String id;
    private String name;
    private String summary;
    private String description;

    private String brand;
    private String model;

    private String mainImageUrl;
    private List<String> categories;
    private List<String> imageUrls;
    private List<String> tags;

    @Field(targetType = FieldType.DECIMAL128)
    private BigDecimal price;

    private Double rating;
    private Long unitsSold;

    private Long stock;
    private Boolean active;

    @Field(targetType = FieldType.DECIMAL128)
    private BigDecimal discount;
    private Boolean onSale;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private ProductStatus status;


    private String tenantId;
    private String storeId;

    private Map<String, String> attributes;
    
}
