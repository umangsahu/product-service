package com.example.first_project.controllers;

import com.example.first_project.models.Product;
import com.example.first_project.services.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {

    ProductService productService;

    public ProductController(ProductService productService){
      this.productService = productService;
    }

    @GetMapping("/{id}")
    public Product getMessage(@PathVariable("id") long id){
        return productService.getSingleProduct(id);
    }
}
