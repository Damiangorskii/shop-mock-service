package com.example.shopmockservice.controller;

import com.example.shopmockservice.model.Product;
import com.example.shopmockservice.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;

import java.util.UUID;

import static org.mockito.Mockito.when;

public class ProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    private WebTestClient webTestClient;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        webTestClient = WebTestClient.bindToController(productController).build();
    }

    @Test
    void should_return_all_products() {
        Product product1 = Product.builder().id(UUID.randomUUID()).name("Product one").build();
        Product product2 = Product.builder().id(UUID.randomUUID()).name("Product two").build();
        when(productService.getProducts()).thenReturn(Flux.just(product1, product2));

        webTestClient.get().uri("/products")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBodyList(Product.class).hasSize(2);
    }
}

