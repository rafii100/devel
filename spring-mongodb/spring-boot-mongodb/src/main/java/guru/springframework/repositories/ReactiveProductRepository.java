package guru.springframework.repositories;

import guru.springframework.domain.Product;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;


/**
 * Created by jt on 1/10/17.
 */
public interface ReactiveProductRepository extends ReactiveMongoRepository<Product, String> {
}
