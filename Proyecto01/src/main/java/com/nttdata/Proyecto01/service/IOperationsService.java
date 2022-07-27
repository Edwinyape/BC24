package com.nttdata.Proyecto01.service;

import com.nttdata.Proyecto01.model.Operations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IOperationsService {

    Mono<String> Deposit(Operations operations);

    Mono<String> Retreat(Operations operations);

    Mono<String> ConsultBalance(String id);

    Flux<Operations> ConsultTransactions(String id);


}
