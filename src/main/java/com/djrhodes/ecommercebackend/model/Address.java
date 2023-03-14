package com.djrhodes.ecommercebackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

/**
 * Address to which the user will be billed/delivered.
 */
@Entity
@Table(name = "address")
public class Address {

    /** Unique id for the address. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    /** First line of the address. */
    @Column(name = "address_line_1", nullable = false, length = 512)
    private String addressLine1;
    /** Second line of the address, if applicable. */
    @Column(name = "address_line_2", length = 512)
    private String addressLine2;
    /** City of the address. */
    @Column(name = "city", nullable = false)
    private String city;
    /** Country of the address. */
    @Column(name = "country", nullable = false, length = 80)
    private String country;
    /** The user the address is associated with. */
    @JsonIgnore
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private LocalUser user;

    /**
     * Gets the user.
     * @return the user.
     */
    public LocalUser getUser() {
        return user;
    }

    /**
     * Sets the user.
     * @param user The user to be set.
     */
    public void setUser(LocalUser user) {
        this.user = user;
    }

    /**
     * Gets the country.
     * @return the country.
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets the country.
     * @param country The country to be set
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Gets the city.
     * @return the city.
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the city.
     * @param city The city to be set.
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Gets the Address Line 2.
     * @return The Address Line 2.
     */
    public String getAddressLine2() {
        return addressLine2;
    }

    /**
     * Sets the Address Line 2.
     * @param addressLine2 The Address Line 2 to be set.
     */
    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    /**
     * Gets the Address Line 1.
     * @return the Address Line 1.
     */
    public String getAddressLine1() {
        return addressLine1;
    }

    /**
     * Sets the Address Line 1.
     * @param addressLine1 The Address Line 1 to be set.
     */
    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    /**
     * Gets the ID of the Address.
     * @return the ID.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the ID of the Address
     * @param id The ID to be set.
     */
    public void setId(Long id) {
        this.id = id;
    }

}