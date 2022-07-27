package com.nttdata.operationsservice.service;

import com.nttdata.operationsservice.model.Operations;
import com.nttdata.operationsservice.model.ProductDetails;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IOperationsService {

    Mono<String> Deposit(Operations operations);

    Mono<String> Retreat(Operations operations);

    Mono<String> ConsultBalance(String id);

    Flux<Operations> ConsultTransactions(String id);

    Mono<ProductDetails> findById(String id);

    Mono<ProductDetails> save(ProductDetails p);

}