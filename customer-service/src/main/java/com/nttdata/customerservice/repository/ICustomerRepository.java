package com.nttdata.customerservice.repository;

import com.nttdata.customerservice.model.Customer;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICustomerRepository extends ReactiveCrudRepository<Customer, String> {
}
