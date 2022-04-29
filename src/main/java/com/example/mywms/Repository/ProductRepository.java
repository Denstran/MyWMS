package com.example.mywms.Repository;

import com.example.mywms.Model.Product;
import com.example.mywms.Model.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByProductName(String productName);

    List<Product> findByProductTypeIn(Set<ProductType> productTypes);
}
