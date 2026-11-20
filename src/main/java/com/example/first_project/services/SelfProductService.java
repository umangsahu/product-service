package com.example.first_project.services;

import com.example.first_project.exceptions.ProductNotFound;
import com.example.first_project.models.Category;
import com.example.first_project.models.Product;
import com.example.first_project.repositories.CategoryRepository;
import com.example.first_project.repositories.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service("SelfProductService")
public class SelfProductService implements  ProductService{
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final CategoryService categoryService;

    public SelfProductService(ProductRepository productRepository, CategoryRepository categoryRepository, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.categoryService = categoryService;
    }
    @Override
    public Product getSingleProduct(Long id) throws ProductNotFound {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFound("Product " + id + " was not found"));
    }


    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }


    @Override
    public Product updateProduct(Long id, Product product) {
        return null;
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        return null;
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Product addNewProduct(Product product) {

        Category category = product.getCategory();

        if (category == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Category is required");
        }

        Long categoryId = category.getId();
        if (categoryId == null) {
            category = categoryService.createCategory(category);
        } else {
            category = categoryRepository.findById(categoryId)
                    .orElseThrow(() -> new ResponseStatusException(
                            HttpStatus.NOT_FOUND, "Category " + categoryId + " was not found"));
        }
        if (product.getProductName() == null || product.getProductName().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product name is required");
        }
        if (product.getProductPrice() < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product price cannot be negative");
        }

        Product newProduct = new Product();
        newProduct.setProductName(product.getProductName());
        newProduct.setProductPrice(product.getProductPrice());
        newProduct.setCategory(category);
        return productRepository.save(newProduct);
    }
}
