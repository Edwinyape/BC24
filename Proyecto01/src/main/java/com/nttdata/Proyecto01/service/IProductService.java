package com.nttdata.Proyecto01.service;

import com.nttdata.Proyecto01.model.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IProductService {

    Flux<Product> findAll();

    Mono<String> save(Product product);

    Flux<Product> findbyIdCustomer(String idcustomer);

    Mono<Product> update(Product product);

    Mono<Void> delete(String id);

    Flux<Product> findbyTipoProduct(String idcustomer, String tipo);

}
