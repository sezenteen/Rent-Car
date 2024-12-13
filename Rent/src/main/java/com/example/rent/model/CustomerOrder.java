package com.example.rent.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;


@Entity
@Table(name = "customer_order")
public class CustomerOrder extends BaseEntity {
    private BigDecimal amount;
    private Date dateCreated;
    private Integer confirmationNumber;
    private Customer customer;

    Set<OrderedProduct> orderedProducts;

    @Column(name = "amount", nullable = false, precision = 9, scale = 2)
    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Basic(optional = false)
    @Column(name = "date_created", insertable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Column(name = "confirmation_number", nullable = false)
    public Integer getConfirmationNumber() {
        return confirmationNumber;
    }

    public void setConfirmationNumber(Integer confirmationNumber) {
        this.confirmationNumber = confirmationNumber;
    }
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @OneToMany(mappedBy = "customerOrder")
    public Set<OrderedProduct> getOrderedProducts(){
        return orderedProducts;
    }
    public void setOrderedProducts(Set<OrderedProduct> orderedProducts){
        this.orderedProducts = orderedProducts;
    }
}
