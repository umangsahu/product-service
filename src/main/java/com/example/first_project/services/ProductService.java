package com.example.first_project.services;

import com.example.first_project.models.Product;

import java.util.List;

public interface ProductService {
    Product getSingleProduct(Long productId);

    List<Product> getAllProduct();

}
