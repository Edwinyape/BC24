package com.nttdata.operationsservice.model;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = "Operaciones")
public class Operations {
    @Id
    private ObjectId id;
    private String idCustomer;
    private String idProduct;
    private String descripcionOperations;
    private Double montoOperations;
    private Date fechaOperations;
}