package org.example.demo03_springsecurity.service;

import org.example.demo03_springsecurity.entity.Product;
import org.example.demo03_springsecurity.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    public Product getProductById(Long id) {
        Optional<Product> optional = productRepository.findById(id);
        return optional.orElse(null);
    }

    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }

    public void updateProduct(Product product) {
        productRepository.save(product);
    }
}
