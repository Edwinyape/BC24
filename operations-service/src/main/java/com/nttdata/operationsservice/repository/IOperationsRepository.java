package com.nttdata.operationsservice.repository;

import com.nttdata.operationsservice.model.Operations;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOperationsRepository extends ReactiveCrudRepository<Operations, String> {
}