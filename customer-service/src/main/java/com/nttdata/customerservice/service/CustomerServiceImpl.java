package com.nttdata.customerservice.service;

import com.nttdata.customerservice.model.Customer;
import com.nttdata.customerservice.repository.ICustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    ICustomerRepository customerRepository;

    @Override
    public Flux<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Mono<Customer> save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Flux<Customer> findbyId(String id) {
        return customerRepository.findAll().filter(x->x.getNumidentidadCustomer().equals(id));
    }

    @Override
    public Mono<Customer> update(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Mono<Void> delete(String id) {
        return customerRepository.deleteById(id);
    }
}
