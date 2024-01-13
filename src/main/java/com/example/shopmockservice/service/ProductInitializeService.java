package com.example.shopmockservice.service;

import com.example.shopmockservice.model.Product;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.Collections;
import java.util.List;

@Getter
@Service
@Slf4j
class ProductInitializeService {

    private List<Product> products;
    private List<Product> gameProducts;
    private List<Product> hardwareProducts;
    private List<Product> softwareToolProducts;

    @PostConstruct
    public void initProducts() {
        try {
            log.info("Reading the data from products.json");
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            InputStream inputStream = new ClassPathResource("products.json").getInputStream();
            products = objectMapper.readValue(inputStream, new TypeReference<>() {
            });
        } catch (Exception e) {
            e.printStackTrace();
            products = Collections.emptyList();
        }
    }

    @PostConstruct
    public void initGames() {
        try {
            log.info("Reading the data from product-external-games.json");
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            InputStream inputStream = new ClassPathResource("products-external-games.json").getInputStream();
            gameProducts = objectMapper.readValue(inputStream, new TypeReference<>() {
            });
        } catch (Exception e) {
            e.printStackTrace();
            gameProducts = Collections.emptyList();
        }
    }

    @PostConstruct
    public void initHardware() {
        try {
            log.info("Reading the data from product-external-hardware.json");
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            InputStream inputStream = new ClassPathResource("products-external-hardware.json").getInputStream();
            hardwareProducts = objectMapper.readValue(inputStream, new TypeReference<>() {
            });
        } catch (Exception e) {
            e.printStackTrace();
            hardwareProducts = Collections.emptyList();
        }
    }

    @PostConstruct
    public void initSoftwareTools() {
        try {
            log.info("Reading the data from product-external-software-tools.json");
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            InputStream inputStream = new ClassPathResource("products-external-software-tools.json").getInputStream();
            softwareToolProducts = objectMapper.readValue(inputStream, new TypeReference<>() {
            });
        } catch (Exception e) {
            e.printStackTrace();
            softwareToolProducts = Collections.emptyList();
        }
    }

}
