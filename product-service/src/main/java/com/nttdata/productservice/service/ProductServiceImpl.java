package com.nttdata.productservice.service;

import com.nttdata.productservice.model.Product;
import com.nttdata.productservice.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    IProductRepository productRepository;

    @Override
    public Flux<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Mono<String> save(Product product) {
        Mono<String> monostring = null;
        return Mono.just(product)
                .flatMap(p->{
                    if(p.getIdCustomer().length()==8 ){
                        if(p.getTipoCuentaProduct().equals("0") || p.getTipoCuentaProduct().equals("1") || p.getTipoCuentaProduct().equals("2") || p.getTipoCuentaProduct().equals("3")){
                            return findbyTipoProduct(product.getIdCustomer(), product.getTipoCuentaProduct())
                                    .hasElements()
                                    .flatMap(aBoolean -> {
                                        if(aBoolean) {
                                            return monostring.just("Usted ya cuenta con una cuenta de este tipo");
                                        }else{
                                            return productRepository.save(p).flatMap(x-> monostring.just("Producto adquirido con éxito"));
                                        }
                                    });
                        }
                    }else if(p.getIdCustomer().length()==11){
                        if(p.getTipoCuentaProduct().equals("0") || p.getTipoCuentaProduct().equals("2")){
                            return monostring.just("Un cliente empresarial no puede tener una cuenta de ahorro o de plazo fijo");
                        }else if(p.getTipoCuentaProduct().equals("1") || p.getTipoCuentaProduct().equals("3")){
                            return productRepository.save(p).flatMap(x-> monostring.just("Producto adquirido con éxito"));
                        }
                    }else{
                        return monostring.just("Ingrese el id de un cliente personal o  empresarial");
                    }
                    return monostring;
                });

    }

    @Override
    public Flux<Product> findbyIdCustomer(String idcustomer) {
        return productRepository.findAll().filter(x->x.getIdCustomer().equals(idcustomer));
    }

    @Override
    public Mono<Product> update(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Mono<Void> delete(String id) {
        return productRepository.deleteById(id);
    }

    @Override
    public Flux<Product> findbyTipoProduct(String idcustomer, String tipo) {
        return findbyIdCustomer(idcustomer).filter(x->x.getTipoCuentaProduct().equals(tipo));
    }

    @Override
    public Mono<Product> findbyId(String id) {
        return productRepository.findById(id);
    }


}
