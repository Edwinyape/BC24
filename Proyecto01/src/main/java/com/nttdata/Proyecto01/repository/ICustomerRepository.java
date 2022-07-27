package com.nttdata.Proyecto01.repository;

import com.nttdata.Proyecto01.model.Customer;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICustomerRepository extends ReactiveCrudRepository<Customer, String> {
}
