package edu.bjtu.reative.services;

import edu.bjtu.reative.models.Product;
import edu.bjtu.reative.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    @Override
    public Mono<Product> findById(String id) {
        return productRepository.findById(id);
    }
    @Override
    public Flux<Product> findAll() {
        return productRepository.findAll();
    }
    @Override
    public Mono<Product> save(Product product) {
        return productRepository.save(product);
    }
    @Override
    public Mono<Void> deleteById(String id) {
        return productRepository.deleteById(id);
    }
}