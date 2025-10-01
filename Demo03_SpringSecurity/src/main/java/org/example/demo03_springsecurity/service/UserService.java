package org.example.demo03_springsecurity.service;

import org.example.demo03_springsecurity.entity.Users;
import org.example.demo03_springsecurity.model.SignUpDto;
import org.example.demo03_springsecurity.repository.RoleRepository;
import org.example.demo03_springsecurity.repository.UserRepository;
import org.example.demo03_springsecurity.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public String signUpUser(SignUpDto signUpDto) {
        if (userRepository.existsByUsername(signUpDto.getUsername())) {
            return "Username is already taken!";
        }
        if (userRepository.existsByEmail(signUpDto.getEmail())) {
            return "Email is already taken!";
        }

        Users user = new Users();
        user.setName(signUpDto.getName());
        user.setUsername(signUpDto.getUsername());
        user.setEmail(signUpDto.getEmail());
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));

        Role role = roleRepository.findByName("ROLE_USER").orElseGet(() -> {
            Role newRole = new Role();
            newRole.setName("ROLE_USER");
            return roleRepository.save(newRole);
        });

        Set<Role> roleSet = new HashSet<>();
        roleSet.add(role);
        user.setRoles(roleSet);

        userRepository.save(user);
        return "success";
    }
}