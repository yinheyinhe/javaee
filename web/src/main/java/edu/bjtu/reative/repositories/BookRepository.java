package edu.bjtu.reative.repositories;

import edu.bjtu.reative.models.Book;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface BookRepository extends ReactiveMongoRepository<Book, String> {

}
