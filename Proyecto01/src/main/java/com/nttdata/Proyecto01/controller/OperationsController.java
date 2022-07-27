package com.nttdata.Proyecto01.controller;

import com.nttdata.Proyecto01.model.Operations;
import com.nttdata.Proyecto01.service.IOperationsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.lang.invoke.MethodHandles;

@RestController
@RequestMapping("/operations")
public class OperationsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Autowired
    private IOperationsService operationsService;

    @GetMapping("/ConsultBalance/{id}")
    public Mono<String> ConsultBalance(@PathVariable String id){
        return operationsService.ConsultBalance(id);
    }

    @GetMapping("/ConsultTransactions/{id}")
    public Flux<Operations> ConsultTransactions(@PathVariable String id){
        return operationsService.ConsultTransactions(id);
    }

    @PostMapping("/Deposit")
    public Mono<String> Deposit(@RequestBody Operations operations){
        return operationsService.Deposit(operations);
    }

    @PostMapping("/Retreat")
    public Mono<String> Retreat(@RequestBody Operations operations){
        return operationsService.Retreat(operations);
    }

}
