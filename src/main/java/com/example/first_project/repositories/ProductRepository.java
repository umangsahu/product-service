package com.example.first_project.repositories;

import com.example.first_project.models.Product;
import com.example.first_project.projections.ProductwithIdAndTitle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {


    List<Product> findByProductPrice(int productPrice);


    @Query("select p.id, p.title from product where p.id = :x")
    List<ProductwithIdAndTitle> randomSearchMethod(Long x);


}
