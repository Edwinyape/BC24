package com.nttdata.customerservice.service;

import com.nttdata.customerservice.model.Customer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ICustomerService {

    Flux<Customer> findAll();

    Mono<Customer> save(Customer customer);

    Flux<Customer> findbyId(String id);

    Mono<Customer> update(Customer customer);

    Mono<Void> delete(String id);

}

