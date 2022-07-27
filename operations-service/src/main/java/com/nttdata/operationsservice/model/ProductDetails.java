package com.nttdata.operationsservice.model;

import lombok.Data;
import org.bson.types.ObjectId;

@Data
public class ProductDetails {
    private ObjectId id;
    private String idCustomer;
    private String descProduct;
    private String tipoCuentaProduct;
    private String numCuentaProduct;
    private Double comisMantenimientoProduct;
    private Integer cantMovimMensualesProduct;
    private Double saldoProduct;
}
