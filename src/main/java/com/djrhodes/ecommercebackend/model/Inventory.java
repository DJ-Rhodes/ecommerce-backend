package com.djrhodes.ecommercebackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

/**
 * Inventory of a product available for purchase from stock.
 */
@Entity
@Table(name = "inventory")
public class Inventory {

    /** Unique ID for the inventory. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    /** The product the inventory is of. */
    @JsonIgnore
    @OneToOne(optional = false, orphanRemoval = true)
    @JoinColumn(name = "product_id", nullable = false, unique = true)
    private Product product;
    /** The quantity currently in stock. */
    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    /**
     * Gets the quantity available.
     * @return the quantity.
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * Sets the available quantity.
     * @param quantity The quantity to be set.
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    /**
     * Gets the product.
     * @return the product.
     */
    public Product getProduct() {
        return product;
    }

    /**
     * Sets the product.
     * @param product The product to be set.
     */
    public void setProduct(Product product) {
        this.product = product;
    }

    /**
     * Get the ID of the inventory.
     * @return the ID.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the inventory ID.
     * @param id The ID to be set.
     */
    public void setId(Long id) {
        this.id = id;
    }

}