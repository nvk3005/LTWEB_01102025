package org.example.demo03_springsecurity.controller;

import org.example.demo03_springsecurity.entity.Product;
import org.example.demo03_springsecurity.model.LoginDto;
import org.example.demo03_springsecurity.model.SignUpDto;
import org.example.demo03_springsecurity.service.ProductService;
import org.example.demo03_springsecurity.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @GetMapping({"/", "/index"})
    public String home(Model model) {
        model.addAttribute("products", productService.getAllProduct());
        return "index";
    }

    @GetMapping("/shop")
    public String shop(Model model) {
        model.addAttribute("products", productService.getAllProduct());
        return "shop";
    }

    @GetMapping("/new")
    public String newProduct(Model model) {
        model.addAttribute("product", new Product());
        return "new_product";
    }

    @PostMapping("/saveProduct")
    public String saveProduct(@ModelAttribute("product") Product product) {
        productService.saveProduct(product);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String showFormForUpdate(@PathVariable Long id, Model model) {
        model.addAttribute("product", productService.getProductById(id));
        return "edit_product";
    }

    @PostMapping("/update/{id}")
    public String updateProduct(@PathVariable Long id, @ModelAttribute("product") Product product) {
        Product existingProduct = productService.getProductById(id);
        existingProduct.setId(id);
        existingProduct.setName(product.getName());
        existingProduct.setBrand(product.getBrand());
        existingProduct.setCategory(product.getCategory());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setDescription(product.getDescription());
        productService.updateProduct(existingProduct);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProductById(id);
        return "redirect:/";
    }

    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("signupdto", new SignUpDto());
        return "signup";
    }

    @GetMapping("/signin")
    public String login(Model model) {
        model.addAttribute("logindto", new LoginDto());
        return "login";
    }

    @PostMapping("/registeruser")
    public String registerUser(@Valid @ModelAttribute("signupdto") SignUpDto signUpDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "signup";
        }
        String res = userService.signUpUser(signUpDto);
        if (!res.equals("success")) {
            model.addAttribute("message", res);
            return "signup";
        }
        return "redirect:/signin?success";
    }
}
