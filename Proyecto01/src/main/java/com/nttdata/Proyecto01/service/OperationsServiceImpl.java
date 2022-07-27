package com.nttdata.Proyecto01.service;

import com.nttdata.Proyecto01.model.Operations;
import com.nttdata.Proyecto01.repository.IOperationsRepository;
import com.nttdata.Proyecto01.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class OperationsServiceImpl implements IOperationsService {

    @Autowired
    IProductRepository productRepository;

    @Autowired
    IOperationsRepository operationsRepository;

    @Override
    public Mono<String> Deposit(Operations operations) {
        return productRepository.findById(operations.getIdProduct())
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
                    return productRepository.save(x).flatMap(r-> operationsRepository.save(operations).flatMap(o -> Mono.just("Deposito realizado con exito")));
                });
    }

    @Override
    public Mono<String> Retreat(Operations operations) {
        return productRepository.findById(operations.getIdProduct())
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
                    return productRepository.save(x).flatMap(r-> operationsRepository.save(operations).flatMap(o -> Mono.just("Retiro realizado con exito")));
                });
    }

    @Override
    public Mono<String> ConsultBalance(String id) {
        return productRepository.findById(id)
                .flatMap(x-> Mono.just("El saldo de su " + x.getDescProduct() + " es :"+x.getSaldoProduct()));
    }

    @Override
    public Flux<Operations> ConsultTransactions(String id) {
        return operationsRepository.findAll().filter(x -> x.getIdProduct().equals(id));
    }
}
