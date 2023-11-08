package com.example.shopmockservice.service;

import com.example.shopmockservice.model.Product;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.concurrent.ThreadLocalRandom;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductInitializeService productInitializeService;

    public Flux<Product> getProducts() {
        return Flux.fromIterable(productInitializeService.getProducts())
                .delaySubscription(Mono.delay(Duration.ofMillis(ThreadLocalRandom.current().nextLong(200, 250))));
    }

}
