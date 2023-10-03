package com.example.shopmockservice.service;

import com.example.shopmockservice.model.Category;
import com.example.shopmockservice.model.Manufacturer;
import com.example.shopmockservice.model.Product;
import com.example.shopmockservice.model.Review;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;

public class ProductServiceTest {

    @Mock
    private ProductInitializeService productInitializeService;

    @InjectMocks
    private ProductService productService;

    private static Product createMockedProduct(final String name, BigDecimal price) {
        return Product.builder()
                .id(UUID.randomUUID())
                .name(name)
                .description("Some description of a product")
                .price(price)
                .manufacturer(Manufacturer.builder()
                        .id(UUID.randomUUID())
                        .name("Some name")
                        .address("Some address 344")
                        .contact("some@email.com")
                        .build())
                .categories(List.of(Category.BABY_PRODUCTS, Category.FURNITURE))
                .createdAt(LocalDateTime.of(2023, 10, 2, 11, 30))
                .updatedAt(LocalDateTime.of(2023, 10, 2, 11, 30))
                .reviews(List.of(
                                Review.builder()
                                        .reviewerName("Some Name")
                                        .comment("Some comment")
                                        .rating(6)
                                        .reviewDate(LocalDateTime.of(2023, 10, 3, 11, 30))
                                        .build(),
                                Review.builder()
                                        .reviewerName("Another Name")
                                        .comment("Another comment")
                                        .rating(6)
                                        .reviewDate(LocalDateTime.of(2023, 10, 3, 10, 30))
                                        .build()
                        )
                )
                .build();
    }

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void should_return_products() {
        List<Product> mockProducts = List.of(
                createMockedProduct("Product one", BigDecimal.valueOf(300.0)),
                createMockedProduct("Product two", BigDecimal.valueOf(250.0)),
                createMockedProduct("Product three", BigDecimal.valueOf(350.0))
        );
        when(productInitializeService.getProducts()).thenReturn(mockProducts);

        Flux<Product> result = productService.getProducts();

        StepVerifier.create(result)
                .expectNextMatches(product -> "Product one".equals(product.getName()))
                .expectNextMatches(product -> "Product two".equals(product.getName()))
                .expectNextMatches(product -> "Product three".equals(product.getName()))
                .verifyComplete();
    }

    @Test
    public void should_return_empty_flux_when_no_products() {
        when(productInitializeService.getProducts()).thenReturn(List.of());

        Flux<Product> result = productService.getProducts();

        StepVerifier.create(result)
                .verifyComplete();
    }
}


