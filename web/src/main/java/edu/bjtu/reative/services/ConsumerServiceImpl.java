package edu.bjtu.reative.services;

import edu.bjtu.reative.models.Consumer;
import edu.bjtu.reative.repositories.ConsumerRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.springframework.stereotype.Service;

@Service
public class ConsumerServiceImpl implements ConsumerService {
    private ConsumerRepository consumerRepository;
    public ConsumerServiceImpl(ConsumerRepository consumerRepository) {
        this.consumerRepository = consumerRepository;
    }
    @Override
    public Mono<Consumer> findById(String id) {
        return consumerRepository.findById(id);
    }
    @Override
    public Flux<Consumer> findAll() {
        return consumerRepository.findAll();
    }
    @Override
    public Mono<Consumer> save(Consumer trainer) {
        return consumerRepository.save(trainer);
    }
    @Override
    public Mono<Void> deleteById(String id) {
        return consumerRepository.deleteById(id);
    }
}
