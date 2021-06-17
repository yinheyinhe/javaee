package com.springboot.jpa_demo.datasource1.repository;

import com.springboot.jpa_demo.datasource1.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepositpry extends JpaRepository<Product,Integer> {
    Product findById(int id);
    Product saveAndFlush(Product product);

}
