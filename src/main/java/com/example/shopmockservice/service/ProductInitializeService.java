package com.example.shopmockservice.service;

import com.example.shopmockservice.model.Product;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.Collections;
import java.util.List;

@Service
@Slf4j
class ProductInitializeService {

    private List<Product> products;

    @PostConstruct
    public void init() {
        try {
            log.info("Reading the data from file");
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

    public List<Product> getProducts() {
        return products;
    }
}
