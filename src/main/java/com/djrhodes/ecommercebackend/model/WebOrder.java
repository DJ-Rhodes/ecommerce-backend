package com.djrhodes.ecommercebackend.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Order placed on the website.
 */
@Entity
@Table(name = "web_order")
public class WebOrder {

    /** Unique ID for the order. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    /** User that placed the order. */
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private LocalUser user;
    /** Address to which the order is shipped. */
    @ManyToOne(optional = false)
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;
    /** Quantities ordered. */
    @OneToMany(mappedBy = "order", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<WebOrderQuantities> quantities = new ArrayList<>();

    /**
     * Gets the Quantities of the order.
     * @return the Quantities.
     */
    public List<WebOrderQuantities> getQuantities() {
        return quantities;
    }

    /**
     * Sets the Quantities of the order.
     * @param quantities The Quantities to be set.
     */
    public void setQuantities(List<WebOrderQuantities> quantities) {
        this.quantities = quantities;
    }

    /**
     * Gets the Address of the order.
     * @return the Address.
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Sets the Address of the order.
     * @param address The Address to be set.
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    /**
     * Gets the User who placed the order.
     * @return the User.
     */
    public LocalUser getUser() {
        return user;
    }

    /**
     * Sets the User who placed the order.
     * @param user The User to be set.
     */
    public void setUser(LocalUser user) {
        this.user = user;
    }

    /**
     * Gets the ID of the order.
     * @return the ID.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the ID of the order.
     * @param id The ID to be set.
     */
    public void setId(Long id) {
        this.id = id;
    }

}