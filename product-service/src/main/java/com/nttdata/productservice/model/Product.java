package com.nttdata.productservice.model;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "Producto")
public class Product {
    @Id
    private ObjectId id;
    private String idCustomer;
    private String descProduct;
    private String tipoCuentaProduct;
    private String numCuentaProduct;
    private Double comisMantenimientoProduct;
    private Integer cantMovimMensualesProduct;
    private Double saldoProduct;
}