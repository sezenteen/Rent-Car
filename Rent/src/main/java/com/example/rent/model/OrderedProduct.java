package com.example.rent.model;


import jakarta.persistence.*;

@Entity
@Table(name = "ordered_product")
public class OrderedProduct {
//    Key vvsgej holboj ajillna
    CustomerOrderKey id;
    CustomerOrder customerOrder;
    Product product;
    int quantity;

    @ManyToOne
    @MapsId("customer_orderId")
    @JoinColumn(name = "customer_order_id")
    public CustomerOrder getCustomerOrder() {
        return customerOrder;
    }
    public void setCustomerOrder(CustomerOrder customerOrder) {
        this.customerOrder = customerOrder;
    }

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    public Product getProduct() {
        return product;
    }
    public void setProduct(Product product) {
        this.product = product;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    @EmbeddedId
    public CustomerOrderKey getId() {
        return id;
    }
    public void setId(CustomerOrderKey id) {
        this.id = id;
    }

}

