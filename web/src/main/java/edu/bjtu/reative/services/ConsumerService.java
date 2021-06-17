package edu.bjtu.reative.services;

import edu.bjtu.reative.models.Consumer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ConsumerService {
    Mono<Consumer> findById(String id);
    Flux<Consumer> findAll();
    Mono<Consumer> save(Consumer trainer);
    Mono<Void> deleteById(String id);
}
