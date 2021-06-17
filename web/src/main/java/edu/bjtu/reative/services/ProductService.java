package edu.bjtu.reative.services;

import edu.bjtu.reative.models.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {
    Mono<Product> findById(String id);
    Flux<Product> findAll();
    Mono<Product> save(Product product);
    Mono<Void> deleteById(String id);
}