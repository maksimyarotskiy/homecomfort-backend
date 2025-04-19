package com.homecomfort.homecomfort.service;

import com.homecomfort.homecomfort.entity.Product;
import com.homecomfort.homecomfort.exception.ProductNotFoundException;
import com.homecomfort.homecomfort.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    public Product createProduct(Product product) {
        if (product == null || product.getName() == null || product.getName().isEmpty()) {
            throw new IllegalArgumentException("Product or its name cannot be null or empty");
        }
        if (product.getPrice() == null || product.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Price must be greater than zero");
        }
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, Product product) {
        Product productForUpdate = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
        if (product == null || product.getName() == null || product.getName().isEmpty()) {
            throw new IllegalArgumentException("Product or its name cannot be null or empty");
        }
        if (product.getPrice() == null || product.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Price must be greater than zero");
        }
        productForUpdate.setCategory(product.getCategory());
        productForUpdate.setDescription(product.getDescription());
        productForUpdate.setName(product.getName());
        productForUpdate.setPrice(product.getPrice());
        return productRepository.save(productForUpdate);
    }

    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
        productRepository.delete(product);
    }
}
