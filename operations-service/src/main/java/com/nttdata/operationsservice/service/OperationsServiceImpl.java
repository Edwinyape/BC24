package com.nttdata.operationsservice.service;

import com.nttdata.operationsservice.model.Operations;
import com.nttdata.operationsservice.model.ProductDetails;
import com.nttdata.operationsservice.repository.IOperationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class OperationsServiceImpl implements IOperationsService {

    WebClient webclient= WebClient.builder()
            .baseUrl("http://localhost:8083")
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .build();
    //@Autowired
    //IProductRepository productRepository;

    @Autowired
    IOperationsRepository operationsRepository;

    @Override
    public Mono<String> Deposit(Operations operations) {
        return findById(operations.getIdProduct())
                .flatMap(x-> {
                    if(x.getSaldoProduct()<operations.getMontoOperations()){
                        return Mono.just("No cuenta con saldo suficiente para realizar esta operacion");
                    }else{
                        x.setSaldoProduct(x.getSaldoProduct()-operations.getMontoOperations());
                    }

                    if(x.getCantMovimMensualesProduct()==0){
                        return Mono.just("Alcanzo el limite maximo de movimientos mensuales");
                    }else if(x.getTipoCuentaProduct().equals("0")){
                        x.setCantMovimMensualesProduct(x.getCantMovimMensualesProduct()-1);
                    }else if(x.getTipoCuentaProduct().equals("2")){
                        x.setCantMovimMensualesProduct(0);
                    }
                    return save(x).flatMap(r-> operationsRepository.save(operations).flatMap(o -> Mono.just("Deposito realizado con exito")));
                });
    }

    @Override
    public Mono<String> Retreat(Operations operations) {

        return findById(operations.getIdProduct())
                .flatMap(x-> {
                    if(x.getSaldoProduct()<operations.getMontoOperations()){
                        return Mono.just("No cuenta con saldo suficiente para realizar esta operacion");
                    }else{
                        x.setSaldoProduct(x.getSaldoProduct()-operations.getMontoOperations());
                    }

                    if(x.getCantMovimMensualesProduct()==0){
                        return Mono.just("Alcanzo el limite maximo de movimientos mensuales");
                    }else if(x.getTipoCuentaProduct().equals("0")){
                        x.setCantMovimMensualesProduct(x.getCantMovimMensualesProduct()-1);
                    }else if(x.getTipoCuentaProduct().equals("2")){
                        x.setCantMovimMensualesProduct(0);
                    }
                    return save(x).flatMap(r-> operationsRepository.save(operations).flatMap(o -> Mono.just("Retiro realizado con exito")));
                });

    }

    @Override
    public Mono<String> ConsultBalance(String id) {
        return findById(id)
                .flatMap(x-> Mono.just("El saldo de su " + x.getDescProduct() + " es :"+x.getSaldoProduct()));

    }

    @Override
    public Flux<Operations> ConsultTransactions(String id) {
        return operationsRepository.findAll().filter(x -> x.getIdProduct().equals(id));
    }

    @Override
    public Mono<ProductDetails> findById(String id) {
        return webclient.get()
                .uri("/product/{id}",id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(ProductDetails.class);
    }

    @Override
    public Mono<ProductDetails> save(ProductDetails p) {
        return webclient.post()
                .uri("/product")
                .body(Mono.just(p), ProductDetails.class)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(ProductDetails.class);
    }

}
