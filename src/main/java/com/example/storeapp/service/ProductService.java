package com.example.storeapp.service;

import com.example.storeapp.model.Category;
import com.example.storeapp.model.Product;
import com.example.storeapp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Transient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    @Transient
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    public void addProduct(Product product) {
        productRepository.save(product);

    }

    public void removeProductById(long id) {
        productRepository.deleteById(id);
    }

    @Transient
    public Optional<Product> getProductById(long id) {
        return productRepository.findById(id);

    }

    @Transient
    public List<Product> getAllProductsByCategoryId(int id) {
        return productRepository.findAllByCategoryId(id);
    }

}
