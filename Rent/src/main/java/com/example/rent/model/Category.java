package com.example.rent.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table( name ="category")

public class Category extends BaseEntity{

    private String name;

    @NotBlank
    @NotEmpty(message = "Барааны нэр оруулна уу!")
    @Column(name="name", length = 45)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
