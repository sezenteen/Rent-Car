package com.example.rent.model;
import jakarta.validation.constraints.NotEmpty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name="product")
public class Product extends BaseEntity{
    private String name;
    private BigDecimal price;
    private String description;
    private Date last_update;
    private Category category;
    Set<OrderedProduct> orderedProducts;

    @NotEmpty(message = "Бүтээгдэхүүний нэр оруулна уу!")
    @Size(min = 2, max = 45, message = "Бүтээгдэхүүний нэр 2-оос 45 хүртэлх тэмдэгч байна!")
    @Column(name = "name", length =45, nullable = false )
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotNull(message = "Бүтээгдэхүүний үнэ оруулна уу!")
    @Column(name="price", nullable = false , precision = 9, scale = 2)
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @NotBlank(message = "Бүтээгдэхүүний тайлбар оруулна уу!")
    @Size(min = 10, max = 200, message = "10-аас 200 тэмдэгтэд багтааж тайлбар оруулна уу!")
    @Column(columnDefinition = "Text")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic(optional = false)
    @Column(name="last_update", insertable = false, updatable = false,columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getLast_update() {
        return last_update;
    }

    public void setLast_update(Date last_update) {
        this.last_update = last_update;
    }

    @NotNull(message = "Бүтээгдэхүүний ангилал сонгоно уу")
    @ManyToOne
    @JoinColumn(name="category_id", nullable = false)
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @OneToMany(mappedBy = "product")
    public Set<OrderedProduct> getOrderedProducts() {
        return orderedProducts;
    }

    public void setOrderedProducts(Set<OrderedProduct> orderedProducts) {
        this.orderedProducts = orderedProducts;
    }
}
