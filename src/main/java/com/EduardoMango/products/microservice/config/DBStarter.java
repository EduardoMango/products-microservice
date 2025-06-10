package com.EduardoMango.products.microservice.config;

import com.EduardoMango.products.microservice.model.documents.Product;
import com.EduardoMango.products.microservice.model.enums.ProductStatus;
import com.EduardoMango.products.microservice.repositories.ProductRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

//@Configuration
public class DBStarter {

    private final ProductRepository productRepository;

    public DBStarter(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    private static final List<String> sampleCategories = List.of("Electrónica", "Hogar", "Juguetes", "Deportes", "Moda");
    private static final List<String> sampleBrands = List.of("Sony", "Samsung", "LG", "Apple", "Nike", "Adidas");
    private static final List<String> sampleModels = List.of("Pro", "Lite", "Plus", "Max", "Ultra", "Basic");
    private static final List<String> sampleTags = List.of("nuevo", "oferta", "popular", "destacado");
    private static final Random random = new Random();


    @PostConstruct
    public void init() {
        List<Product> products = new ArrayList<>();

        for (int i = 1; i <= 50; i++) {
            String brand = getRandom(sampleBrands);
            String category = getRandom(sampleCategories);

            // Crear BigDecimal desde String para mantener la precisión exacta
            BigDecimal price = new BigDecimal(String.format("%d.%02d",
                    50 + random.nextInt(500),
                    random.nextInt(100))).setScale(2, RoundingMode.HALF_UP);

            BigDecimal discount = random.nextBoolean()
                    ? new BigDecimal(String.format("%d.%02d",
                    random.nextInt(30),
                    random.nextInt(100))).setScale(2, RoundingMode.HALF_UP)
                    : null;

            boolean onSale = discount != null;

            Product product = Product.builder()
                    .name(brand + " " + getRandom(sampleModels))
                    .summary("Resumen del producto " + i)
                    .description("Descripción detallada del producto " + i)
                    .brand(brand)
                    .model(getRandom(sampleModels))
                    .mainImageUrl("https://example.com/images/" + i + "/main.jpg")
                    .categories(List.of(category))
                    .imageUrls(List.of(
                            "https://example.com/images/" + i + "/1.jpg",
                            "https://example.com/images/" + i + "/2.jpg"
                    ))
                    .tags(random.nextBoolean() ?
                            List.of(getRandom(sampleTags), getRandom(sampleTags)) :
                            List.of(getRandom(sampleTags)))

                    .price(price)
                    .rating(random.nextDouble() * 5)
                    .unitsSold(random.nextLong(1000))
                    .stock(random.nextLong(100))
                    .active(true)
                    .discount(discount)
                    .onSale(onSale)
                    .status(onSale ? ProductStatus.AVAILABLE :
                            random.nextBoolean() ? ProductStatus.AVAILABLE :
                                    ProductStatus.OUT_OF_STOCK)
                    .tenantId("tenant-" + (1 + random.nextInt(3)))
                    .storeId("store-" + (1 + random.nextInt(5)))
                    .attributes(Map.of(
                            "color", getRandom(List.of("Rojo", "Azul", "Negro", "Blanco")),
                            "material", getRandom(List.of("Plástico", "Metal", "Tela", "Cuero"))
                    ))

                    .build();

            products.add(product);
        }

        // Limpiar la colección antes de insertar nuevos datos
        productRepository.deleteAll();
        productRepository.saveAll(products);
    }

    private <T> T getRandom(List<T> list) {
        return list.get(random.nextInt(list.size()));
    }
}
