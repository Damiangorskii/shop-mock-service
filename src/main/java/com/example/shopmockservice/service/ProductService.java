package com.example.shopmockservice.service;

import com.example.shopmockservice.model.Product;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductInitializeService productInitializeService;

    public Flux<Product> getProducts() {
        return Flux.fromIterable(productInitializeService.getProducts())
                .delaySubscription(Mono.delay(Duration.ofMillis(200)));
    }

    public Flux<Product> getGameProducts() {
        return Flux.fromIterable(productInitializeService.getGameProducts())
                .delaySubscription(Mono.delay(Duration.ofMillis(250)));
    }

    public Flux<Product> getHardwareProducts() {
        return Flux.fromIterable(productInitializeService.getHardwareProducts())
                .delaySubscription(Mono.delay(Duration.ofMillis(230)));
    }

    public Flux<Product> getSoftwareToolProducts() {
        return Flux.fromIterable(productInitializeService.getSoftwareToolProducts())
                .delaySubscription(Mono.delay(Duration.ofMillis(215)));
    }

}
