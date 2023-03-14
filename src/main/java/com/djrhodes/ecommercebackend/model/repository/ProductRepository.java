package com.djrhodes.ecommercebackend.model.repository;

import com.djrhodes.ecommercebackend.model.Product;
import org.springframework.data.repository.ListCrudRepository;

/**
 * Repository for accessing Product data.
 */
public interface ProductRepository extends ListCrudRepository<Product, Long> {
}
