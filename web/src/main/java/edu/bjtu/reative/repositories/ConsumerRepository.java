package edu.bjtu.reative.repositories;

import edu.bjtu.reative.models.Consumer;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ConsumerRepository extends ReactiveMongoRepository<Consumer, String> {

}
