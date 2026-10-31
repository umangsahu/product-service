package com.example.first_project.services;

import com.example.first_project.dtos.FakeStoreProductDto;
import com.example.first_project.models.Category;
import com.example.first_project.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class FakeStoreProductService implements ProductService {
    private RestTemplate restTemplate;

    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Product getSingleProduct(Long productId) {
        FakeStoreProductDto fakeStoreProductDto = restTemplate.getForObject("https://fakestoreapi.com/products/" + productId, FakeStoreProductDto.class);
        Product product = new Product();
        if (fakeStoreProductDto == null) return product;
        product.setId((long) fakeStoreProductDto.getId());
        product.setProductName(fakeStoreProductDto.getTitle());
        product.setProductPrice((int) fakeStoreProductDto.getPrice());
        Category category = new Category();
        category.setCategoryName(fakeStoreProductDto.getCategory());
        product.setCategory(category);


        return product;
    }

    @Override
    public List<Product> getAllProduct() {
        return List.of();
    }
}
