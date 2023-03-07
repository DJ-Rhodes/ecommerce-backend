package com.djrhodes.ecommercebackend.model;

import jakarta.persistence.*;

/**
 * The quantity of a product ordered.
 */
@Entity
@Table(name = "web_order_quantities")
public class WebOrderQuantities {

    /** Unique ID for the quantity. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    /** Product ordered. */
    @ManyToOne(optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
    /** The quantity being ordered. */
    @Column(name = "quantity", nullable = false)
    private Integer quantity;
    /** The order itself. */
    @ManyToOne(optional = false)
    @JoinColumn(name = "order_id", nullable = false)
    private WebOrder order;

    /**
     * Gets the Order.
     * @return the Order.
     */
    public WebOrder getOrder() {
        return order;
    }

    /**
     * Sets the Order.
     * @param order The Order to be set.
     */
    public void setOrder(WebOrder order) {
        this.order = order;
    }

    /**
     * Gets the Quantity being ordered.
     * @return the Quantity.
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * Sets the Quantity being ordered.
     * @param quantity The Quantity to be set.
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    /**
     * Gets the Product ordered.
     * @return the Product.
     */
    public Product getProduct() {
        return product;
    }

    /**
     * Sets the Product being ordered.
     * @param product The Product to be set.
     */
    public void setProduct(Product product) {
        this.product = product;
    }

    /**
     * Gets the ID of the quantity.
     * @return the ID.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the ID of the quantity.
     * @param id The ID to be set.
     */
    public void setId(Long id) {
        this.id = id;
    }

}