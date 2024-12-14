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
    @NotBlank(message = "Image URL is required!")
    @Column(name = "image_url", length = 255) // Matches database column name
    private String imageUrl;


    public Category(String name, String imageUrl) {
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public Category() {

    }

    @NotBlank
    @NotEmpty(message = "Барааны нэр оруулна уу!")
    @Column(name="name", length = 45)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
