package com.example.first_project.controllers;

import com.example.first_project.exceptions.ProductNotFound;
import com.example.first_project.models.Product;
import com.example.first_project.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(@Qualifier("SelfProductService") ProductService productService) {
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


    @PatchMapping("/{id}")
    public Product updateProduct(@PathVariable("id") Long id, @RequestBody Product product) {
        return productService.updateProduct(id, product);

    }

    @PutMapping("/{id}")
    public Product ReplaceProduct(@PathVariable Long Id, @RequestBody Product product) {
        return null;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product addNewProduct(@RequestBody Product product) {
        return productService.addNewProduct(product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
    }
}
