package com.nttdata.productservice.controller;

import com.nttdata.productservice.model.Product;
import com.nttdata.productservice.service.IProductService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.lang.invoke.MethodHandles;

@Slf4j
@RestController
@RequestMapping("/product")
public class ProductController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    @Autowired
    private IProductService productService;

    @GetMapping("/{dato1}/{dato2}")
    public Flux<Product> prueba(@PathVariable String dato1, @PathVariable String dato2){
        return productService.findbyTipoProduct(dato1, dato2);
    }

    @GetMapping
    public Flux<Product> List(){
        return productService.findAll();
    }

    @PostMapping
    public Mono<String> Register(@RequestBody Product product){
        LOGGER.info("Validacion de Registro");
        return productService.save(product);
    }

    @PutMapping
    public Mono<Product> update(@RequestBody Product product){
        LOGGER.info("Validacion de Actualizacion");
        return productService.update(product);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable String id){
        return productService.delete(id);
    }

    @GetMapping("/{id}")
    public Mono<Product> findbyId(@PathVariable String id){
        return productService.findbyId(id);
    }
}
