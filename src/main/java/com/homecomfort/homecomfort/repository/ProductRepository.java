package com.homecomfort.homecomfort.repository;

import com.homecomfort.homecomfort.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
