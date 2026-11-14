package com.example.first_project.controllers;

import com.example.first_project.exceptions.ProductNotFound;
import com.example.first_project.models.Product;
import com.example.first_project.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getMessage(@PathVariable("id") long id) throws ProductNotFound {
        ResponseEntity<Product> responseEntity;
        responseEntity = new ResponseEntity<>(
                productService.getSingleProduct(id),
                HttpStatus.OK
        );
        return responseEntity;
    }

    @GetMapping()
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    public void deleteProduct(Long productId) {

    }

    @PatchMapping("/{id}")
    public Product updateProduct(@PathVariable("id") Long id, @RequestBody Product product) {
        return productService.updateProduct(id, product) ;

    }

    @PutMapping("/{id}")
    public Product ReplaceProduct(@PathVariable Long Id, @RequestBody Product product) {
        return null;
    }

}
