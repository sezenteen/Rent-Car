package com.example.rent.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable //Virtual Id vvsgegch
public class CustomerOrderKey implements Serializable {
    Long customer_orderId;
    Long productId;
    @Column(name = "customer_order_id")
    public Long getCustomer_orderId() {
        return customer_orderId;
    }
    public void setCustomer_orderId(Long customer_orderId) {
        this.customer_orderId = customer_orderId;
    }

    @Column(name = "product_id")
    public Long getProductId() {
        return productId;
    }


    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
