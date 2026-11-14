package com.example.first_project.services;

import com.example.first_project.dtos.FakeStoreProductDto;
import com.example.first_project.exceptions.ProductNotFound;
import com.example.first_project.models.Category;
import com.example.first_project.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductService implements ProductService {
    private RestTemplate restTemplate;

    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Product getSingleProduct(Long productId) throws ProductNotFound {
        FakeStoreProductDto fakeStoreProductDto = restTemplate.getForObject("https://fakestoreapi.com/products/" + productId, FakeStoreProductDto.class);
        Product product = new Product();
        if (fakeStoreProductDto == null){
            throw new ProductNotFound("Product with the id"+ productId+" doesn't exist!");
        }
        product.setId((long) fakeStoreProductDto.getId());
        product.setProductName(fakeStoreProductDto.getTitle());
        product.setProductPrice((int) fakeStoreProductDto.getPrice());
        Category category = new Category();
        category.setCategoryName(fakeStoreProductDto.getCategory());
        product.setCategory(category);


        return product;
    }

    @Override
    public List<Product> getAllProducts() {
        FakeStoreProductDto[] fakeStoreProductDto = restTemplate.getForObject("https://fakestoreapi.com/products", FakeStoreProductDto[].class);

        List<Product> products = new ArrayList<>();
        if (fakeStoreProductDto == null) return products;

        for (FakeStoreProductDto fakeStoreProductDto1 : fakeStoreProductDto) {
            products.add(convertFakeStoreDataToProduct(fakeStoreProductDto1));
        }
        return products;
    }

    private Product convertFakeStoreDataToProduct(FakeStoreProductDto fakeStoreProductDto) {
        Product product = new Product();
        product.setId((long) fakeStoreProductDto.getId());
        product.setProductName(fakeStoreProductDto.getTitle());
        product.setProductPrice((int) fakeStoreProductDto.getPrice());
        Category category = new Category();
        category.setCategoryName(fakeStoreProductDto.getCategory());
        product.setCategory(category);
        return product;
    }

    @Override
    public Product updateProduct(Long id, Product product) {

        String url = "https://fakestoreapi.com/products/" + id;

        FakeStoreProductDto response = restTemplate.patchForObject(
                url,
                product,
                FakeStoreProductDto.class
        );
        if(response == null) return null;
        return convertFakeStoreDataToProduct(response);
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        return null;
    }

    @Override
    public void deleteProduct(Long id) {

    }
}
