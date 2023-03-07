package com.djrhodes.ecommercebackend.model;

import jakarta.persistence.*;

/**
 * Product available for purchase.
 */
@Entity
@Table(name = "product")
public class Product {

    /** Unique ID for the product. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    /** Name of the product. */
    @Column(name = "name", nullable = false, unique = true)
    private String name;
    /** Short description of the product. */
    @Column(name = "short_description", nullable = false)
    private String shortDescription;
    /** Long description of the product. */
    @Column(name = "long_description", length = 512)
    private String longDescription;
    /** Price of the product. */
    @Column(name = "price", nullable = false)
    private Double price;
    /** Inventory of the product. */
    @OneToOne(mappedBy = "product", cascade = CascadeType.REMOVE, optional = false, orphanRemoval = true)
    private Inventory inventory;

    /**
     * Gets the Inventory of the product.
     * @return the Inventory.
     */
    public Inventory getInventory() {
        return inventory;
    }

    /**
     * Sets the Inventory of the product.
     * @param inventory The invetory to be set.
     */
    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    /**
     * Gets the Price of the product.
     * @return the Price.
     */
    public Double getPrice() {
        return price;
    }

    /**
     * Sets the Price of the product.
     * @param price The Price to be set.
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * Gets the Long Description of the product.
     * @return the Long Description.
     */
    public String getLongDescription() {
        return longDescription;
    }

    /**
     * Sets the Long Description of the product.
     * @param longDescription The Long Description to be set.
     */
    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    /**
     * Returns the Short Description of the product.
     * @return the Short Description.
     */
    public String getShortDescription() {
        return shortDescription;
    }

    /**
     * Sets the Short Description of the product.
     * @param shortDescription The Short Description to be set.
     */
    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    /**
     * Gets the Name of the product.
     * @return the Name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the Name of the product.
     * @param name The Name to be set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the ID of the product.
     * @return the ID.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the ID of the product.
     * @param id The ID to be set.
     */
    public void setId(Long id) {
        this.id = id;
    }

}