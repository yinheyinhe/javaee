package edu.bjtu.reative.controllers;

import edu.bjtu.reative.models.Consumer;
import edu.bjtu.reative.services.ConsumerService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.BodyInserters.fromPublisher;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component

public class ConsumerHandler {
    private final ConsumerService consumerService;
    public ConsumerHandler(ConsumerService t) {
        this.consumerService = t;
    }
    public Mono<ServerResponse> findById(ServerRequest request) {
        String id = request.pathVariable("id");
        return ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(consumerService.findById(id), Consumer.class);
    }
    public Mono<ServerResponse> findAll(ServerRequest request) {
        return ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(consumerService.findAll(), Consumer.class);
    }
    public Mono<ServerResponse> save(ServerRequest request) {
        final Mono<Consumer> trainer = request.bodyToMono(Consumer.class);
        return ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(fromPublisher(trainer.flatMap(consumerService::save), Consumer.class));
    }
    public Mono<ServerResponse> delete(ServerRequest request) {
        String id = request.pathVariable("id");
        return ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(consumerService.deleteById(id), Void.class);
    }
}

