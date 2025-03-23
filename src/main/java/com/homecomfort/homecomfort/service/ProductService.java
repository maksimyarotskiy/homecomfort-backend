package com.homecomfort.homecomfort.service;

import com.homecomfort.homecomfort.entity.Category;
import com.homecomfort.homecomfort.entity.Product;
import com.homecomfort.homecomfort.exception.ProductNotFoundException;
import com.homecomfort.homecomfort.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return productRepository.findById(id).orElse(null);
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, Product product) {
        Product productForUpdate = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
        product.setCategory(product.getCategory());
        product.setDescription(product.getDescription());
        product.setName(product.getName());
        product.setPrice(product.getPrice());
        return productRepository.save(productForUpdate);
    }

    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
        productRepository.delete(product);
    }
}
