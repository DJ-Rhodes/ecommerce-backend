package com.djrhodes.ecommercebackend.service;

import com.djrhodes.ecommercebackend.model.Product;
import com.djrhodes.ecommercebackend.model.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service for handling product actions.
 */
@Service
public class ProductService {

    /** The Product Repository. */
    private ProductRepository productRepository;

    /**
    * Constructor for spring injection.
    * @param productRepository The Product Repository.
    */
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    
    /**
     * Gets the all products available.
     * @return The list of products.
     */
    public List<Product> getProducts() {
        return productRepository.findAll();
    }
}
