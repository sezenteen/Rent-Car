package com.example.rent.model.Dtos;

import com.example.rent.model.auth.Role;
import lombok.Data;

import java.util.Set;

@Data
public class UserDTO {
// 2 Entity-ийн мэдээллийг нэгтгэж нэг мэдээлэл болгож ашиглахад хэрэглэнэ,

    private String username;
    private String password;
    private Set<Role> roles;
    private String level;
    private String firstName;
    private String lastName;
    private int age;
}
