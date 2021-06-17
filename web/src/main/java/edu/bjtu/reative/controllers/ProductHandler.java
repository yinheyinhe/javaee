package edu.bjtu.reative.controllers;

import edu.bjtu.reative.models.Product;
import edu.bjtu.reative.services.ProductService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.BodyInserters.fromPublisher;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
public class ProductHandler {
    private final ProductService productService;
    public ProductHandler(ProductService productService) {
        this.productService = productService;
    }
    public Mono<ServerResponse> findById(ServerRequest request) {
        String id = request.pathVariable("id");
        return ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(productService.findById(id), Product.class);
    }
    public Mono<ServerResponse> findAll(ServerRequest request) {
        return ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(productService.findAll(), Product.class);
    }
    public Mono<ServerResponse> save(ServerRequest request) {
        final Mono<Product> user = request.bodyToMono(Product.class);
        return ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(fromPublisher(user.flatMap(productService::save), Product.class));
    }
    public Mono<ServerResponse> delete(ServerRequest request) {
        String id = request.pathVariable("id");
        return ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(productService.deleteById(id), Void.class);
    }
}