package com.djrhodes.ecommercebackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * User for authentication on the website.
 */
@Entity
@Table(name = "local_user")
public class LocalUser {

    /** Unique ID for the user. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    /** Username for the user. */
    @Column(name = "username", nullable = false, unique = true, length = 20)
    private String username;
    /** Password for the user. */
    @JsonIgnore
    @Column(name = "password", nullable = false, length = 1000)
    private String password;
    /** Email address of the user. */
    @Column(name = "email", nullable = false, unique = true, length = 320)
    private String email;
    /** First name of the user. */
    @Column(name = "first_name", nullable = false)
    private String firstName;
    /** Last name of the user. */
    @Column(name = "last_name", nullable = false)
    private String lastName;
    /** Addresses associated with the user. */
    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Address> addresses = new ArrayList<>();
    /** Verification tokens sent to the user. */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VerificationToken> verificationTokens = new ArrayList<>();

    /**
     * Gets the list of VerificationTokens sent to the user.
     * @return The list of tokens.
     */
    public List<VerificationToken> getVerificationTokens() {
        return verificationTokens;
    }

    /**
     * Sets the list of VerificationTokens sent to the user.
     * @param verificationTokens The list of tokens.
     */
    public void setVerificationTokens(List<VerificationToken> verificationTokens) {
        this.verificationTokens = verificationTokens;
    }

    /**
     * Gets the addresses associated with the user.
     * @return the Addresses.
     */
    public List<Address> getAddresses() {
        return addresses;
    }

    /**
     * Sets the addresses associated with the user.
     * @param addresses The addresses to be set.
     */
    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    /**
     * Gets the Last Name of the user.
     * @return the Last Name.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the Last Name of the user.
     * @param lastName The Last Name to be set.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the First Name of the user.
     * @return the Fisrt Name.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the First Name of the user.
     * @param firstName The First Name to be set.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the email address of the user.
     * @return the Email address.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the Email address of the user.
     * @param email The Email to be set.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the Password of the user.
     * @return The Password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the Password of the user.
     * @param password The Password to be set.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the Username of the user.
     * @return The Username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the Username of the user.
     * @param username The Username to be set.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the ID of the user.
     * @return The ID.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the ID of the user.
     * @param id The ID to be set.
     */
    public void setId(Long id) {
        this.id = id;
    }

}