package com.nttdata.Proyecto01.repository;

import com.nttdata.Proyecto01.model.Product;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductRepository extends ReactiveCrudRepository<Product, String> {
}
