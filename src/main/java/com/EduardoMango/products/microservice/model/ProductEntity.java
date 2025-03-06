package com.EduardoMango.products.microservice.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.math.BigDecimal;
import java.util.List;

@Document(collection = "productos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductEntity {

    @Id
    private String id;
    private String name;
    private String description;
    private String mainImageUrl;
    private List<String> imageUrls;

    @Field(targetType = FieldType.DECIMAL128)
    private BigDecimal price;

    @Min(0)
    @Max(5)
    private Double rating;

    private Long unitsSold;
    @Min(0)
    private Long stock;
    private Boolean active;

    @Field(targetType = FieldType.DECIMAL128)
    private BigDecimal discount;

    private String sellerId;

    
}
