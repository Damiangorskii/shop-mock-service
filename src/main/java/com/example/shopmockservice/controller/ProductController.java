package com.example.shopmockservice.controller;

import com.example.shopmockservice.model.Product;
import com.example.shopmockservice.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public Flux<Product> getAllProducts() {
        return productService.getProducts();
    }

    @GetMapping("/external/games")
    public Flux<Product> getExternalGameProducts() {
        return productService.getGameProducts();
    }

    @GetMapping("/external/hardware")
    public Flux<Product> getExternalHardwareProducts() {
        return productService.getHardwareProducts();
    }

    @GetMapping("/external/software-tools")
    public Flux<Product> getExternalSoftwareToolProducts() {
        return productService.getSoftwareToolProducts();
    }
}
