package com.example.rent.controller;

import com.example.rent.model.Dtos.UserDTO;
import com.example.rent.model.auth.User;
import com.example.rent.repository.auth.RoleRepository;
import com.example.rent.repository.auth.UserRepository;
import com.example.rent.service.auth.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
    private final RoleRepository roleRepository;
    private final UserService userService;
    private UserRepository userRepository;

    private AuthenticationManager authenticationManager;

    @Autowired
    public UserController(RoleRepository roleRepository, UserRepository userRepository, AuthenticationManager authenticationManager, UserService userService) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.userService = userService;
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        List<String> levels = new ArrayList<>();
        levels.add("BEGINNER");
        levels.add("INTERMEDIATE");
        levels.add("ADVANCED");

        model.addAttribute("roles", roleRepository.findAll());
        model.addAttribute("user", new User());
        model.addAttribute("levels", levels);
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid UserDTO userDTO) {
        userService.save(new User(
                userDTO.getUsername(),
                userDTO.getPassword(),
                userDTO.getLevel(),
                userDTO.getFirstName(),
                userDTO.getLastName(),
                userDTO.getAge(),
                userDTO.getRoles())
        );
        return "redirect:/login";
    }

    @GetMapping("/users/profile")
    public String userProfile(Model model, Principal principal) {
        User user = userRepository.findByUsername(principal.getName());
        model.addAttribute("user", user);
        return "profile";
    }
    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }
}
