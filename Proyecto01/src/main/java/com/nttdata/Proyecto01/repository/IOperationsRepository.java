package com.nttdata.Proyecto01.repository;

import com.nttdata.Proyecto01.model.Operations;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOperationsRepository extends ReactiveCrudRepository<Operations, String> {
}
