package org.example.demo02_springsecurity.controller;

import org.example.demo02_springsecurity.entity.UserInfo;
import org.example.demo02_springsecurity.model.Customer;
import org.example.demo02_springsecurity.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @GetMapping("/hello")
    public String hello() {
        return "Hello World!";
    }

    @GetMapping("/getCustomerList")
    public List<Customer> getCustomerList() {
        return Arrays.asList(
                new Customer(1L, "John Doe", "john@example.com"),
                new Customer(2L, "Jane Doe", "jane@example.com")
        );
    }

    @PostMapping("/addUser")
    public String addUser(@RequestBody UserInfo userInfo) {
        userInfoRepository.save(userInfo);
        return "User added";
    }
}
