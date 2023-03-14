package com.djrhodes.ecommercebackend.api.controller.product;

import com.djrhodes.ecommercebackend.model.Product;
import com.djrhodes.ecommercebackend.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller to handle requests to create, update and view products.
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    /** The Product Service. */
    private ProductService productService;

    /**
     * Constructor for spring injection.
     * @param productService The Product Service.
     */
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Gets the list of products available.
     * @return The list of products.
     */
    @GetMapping
    public List<Product> getProducts() {
        return productService.getProducts();
    }
}
