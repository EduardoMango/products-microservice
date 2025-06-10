package com.EduardoMango.products.microservice.mappers;

import com.EduardoMango.products.microservice.model.dto.ProductResponse;
import com.EduardoMango.products.microservice.model.documents.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductResponse toDTO(Product product);
    Product toEntity(ProductResponse dto);
}

