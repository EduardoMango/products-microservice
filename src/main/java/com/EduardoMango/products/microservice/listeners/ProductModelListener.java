package com.EduardoMango.products.microservice.listeners;

import com.EduardoMango.products.microservice.model.documents.Product;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class ProductModelListener extends AbstractMongoEventListener<Product> {

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Product> event) {
        Product product = event.getSource();

        if (product.getCreatedAt() == null) {
            product.setCreatedAt(LocalDateTime.now());
        }
        if (product.getId() == null) {
            product.setId(UUID.randomUUID().toString());
        }
       if (product.getRating() == null) {
           product.setRating(0.0);
       }
       if (product.getUnitsSold() == null) {
           product.setUnitsSold(0L);
       }
        product.setUpdatedAt(LocalDateTime.now());

    }
}
