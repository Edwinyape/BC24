package com.nttdata.Proyecto01.controller;

import com.nttdata.Proyecto01.model.Customer;
import com.nttdata.Proyecto01.service.ICustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.lang.invoke.MethodHandles;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    @Autowired
    private ICustomerService customerService;

    @GetMapping
    public Flux<Customer> List(){
        return customerService.findAll();
    }

    @PostMapping
    public Mono<Customer> Register(@RequestBody Customer customer){
        return customerService.save(customer);
    }

    @PutMapping
    public Mono<Customer> update(@RequestBody Customer customer){
        LOGGER.info("Validacion de Actualizacion");
        return customerService.update(customer);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable String id){
        return customerService.delete(id);
    }
}
