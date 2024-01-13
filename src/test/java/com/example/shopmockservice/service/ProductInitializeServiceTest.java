package com.example.shopmockservice.service;

import com.example.shopmockservice.model.Product;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductInitializeServiceTest {

    private ProductInitializeService productInitializeService;
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        productInitializeService = new ProductInitializeService();
    }

    @Test
    void should_init_products_successfully() throws IOException {
        InputStream inputStream = new ClassPathResource("products.json").getInputStream();
        List<Product> expectedProducts = objectMapper.readValue(inputStream, new TypeReference<>() {
        });

        productInitializeService.initProducts();

        assertEquals(expectedProducts.size(), (long) productInitializeService.getProducts().size());
    }

    @Test
    void should_init_games_successfully() throws IOException {
        InputStream inputStream = new ClassPathResource("products-external-games.json").getInputStream();
        List<Product> expectedProducts = objectMapper.readValue(inputStream, new TypeReference<>() {
        });

        productInitializeService.initGames();

        assertEquals(expectedProducts.size(), (long) productInitializeService.getGameProducts().size());
    }

    @Test
    void should_init_hardware_successfully() throws IOException {
        InputStream inputStream = new ClassPathResource("products-external-hardware.json").getInputStream();
        List<Product> expectedProducts = objectMapper.readValue(inputStream, new TypeReference<>() {
        });

        productInitializeService.initHardware();

        assertEquals(expectedProducts.size(), (long) productInitializeService.getHardwareProducts().size());
    }

    @Test
    void should_init_software_tools_successfully() throws IOException {
        InputStream inputStream = new ClassPathResource("products-external-software-tools.json").getInputStream();
        List<Product> expectedProducts = objectMapper.readValue(inputStream, new TypeReference<>() {
        });

        productInitializeService.initSoftwareTools();

        assertEquals(expectedProducts.size(), (long) productInitializeService.getSoftwareToolProducts().size());
    }
}


