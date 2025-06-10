package com.EduardoMango.products.microservice.services;

import com.EduardoMango.products.microservice.model.documents.Product;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import org.springframework.data.mongodb.core.query.Query;
import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductFilteringService {

    private final MongoTemplate mongoTemplate;

    public ProductFilteringService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public Query buildFilterQuery(String categoria,
                                  BigDecimal precioMin,
                                  BigDecimal precioMax,
                                  Boolean isDiscounted) {

        Query query = new Query();

        // Filtro de precio usando DECIMAL128
        if (precioMin != null && precioMax != null) {
            query.addCriteria(new Criteria().andOperator(
                    Criteria.where("price").gte(new org.bson.types.Decimal128(precioMin)),
                    Criteria.where("price").lte(new org.bson.types.Decimal128(precioMax))
            ));

        } else if (precioMin != null) {
            query.addCriteria(Criteria.where("price")
                    .gte(new org.bson.types.Decimal128(precioMin)));
        } else if (precioMax != null) {
            query.addCriteria(Criteria.where("price")
                    .lte(new org.bson.types.Decimal128(precioMax)));
        }

        // FILTRO DE DESCUENTO
        if (isDiscounted != null) {
            if (isDiscounted) {
                query.addCriteria(Criteria.where("discount").ne(null));
            } else {
                query.addCriteria(Criteria.where("discount").is(null));
            }
        }

        // FILTRO DE CATEGORÍA
        if (categoria != null && !categoria.isBlank()) {
            query.addCriteria(Criteria.where("categories").in(categoria));
        }

        // Log de la query final
        System.out.println("Query final: " + query);

        return query;
    }



}
