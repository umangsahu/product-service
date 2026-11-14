package com.example.first_project.services;

import com.example.first_project.exceptions.ProductNotFound;
import com.example.first_project.models.Product;

import java.util.List;

public interface ProductService {
    Product getSingleProduct(Long productId) throws ProductNotFound;

    List<Product> getAllProducts();

    Product updateProduct(Long id, Product product);

    Product replaceProduct(Long id, Product product);


    void deleteProduct(Long id);

}
