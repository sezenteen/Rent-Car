package com.example.rent.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import javax.validation.constraints.Pattern;

@Entity
@Table(name = "customer")
public class Customer {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String cityRegion;
    private String ccNumber;

    public Customer() {

    }

    public Customer(Long id,String name, String email, String phone, String address, String city_region, String cc_number) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.cityRegion = city_region;
        this.ccNumber = cc_number;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    public Long getId() {
        return id;
    }


    @NotBlank
    @Column(name = "name", length = 45, nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Email
    @NotBlank
    @Column(name = "email", length = 45, nullable = true)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Pattern(regexp = "[0-9]{10}")
    @Column(name = "phone", length = 45)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @NotEmpty(message = "Хаягаа оруулна уу!")
    @Column(name = "address", length = 45)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    @Column(name = "city_region", length = 2)
    public String getCity_region() {
        return cityRegion;
    }

    public void setCity_region(String city_region) {
        this.cityRegion = city_region;
    }

    @Column(name = "cc_number", length = 19)
    public String getCc_number() {
        return ccNumber;
    }

    public void setCc_number(String cc_number) {
        this.ccNumber = cc_number;
    }

}
