    package com.EduardoMango.products.microservice.hateoas;

import com.EduardoMango.products.microservice.controllers.ProductController;
import com.EduardoMango.products.microservice.model.documents.Product;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ProductModelAssembler implements RepresentationModelAssembler<Product, EntityModel<Product>> {


    @Override
    public EntityModel<Product> toModel(Product product) {
        return EntityModel.of(product,
                linkTo(methodOn(ProductController.class).getById(product.getId())).withSelfRel()
        );
    }

    public EntityModel<Product> toModelForSingle(Product product) {
        return EntityModel.of(product,
                linkTo(methodOn(ProductController.class).getById(product.getId())).withSelfRel(),
                linkTo(methodOn(ProductController.class).getAll(0, 25,null,null,null,null)).withRel("productos")
        );
    }


}
