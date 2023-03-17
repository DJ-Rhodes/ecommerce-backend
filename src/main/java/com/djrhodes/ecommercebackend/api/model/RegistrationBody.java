package com.djrhodes.ecommercebackend.api.model;

import jakarta.validation.constraints.*;

/**
 * The information require to register a new user.
 */
public class RegistrationBody {

    /** The username. */
    @NotNull
    @NotBlank
    @Size(min = 3, max = 320)
    private String username;
    /** The user's email address. */
    @NotNull
    @NotBlank
    @Email
    private String email;
    /** The user's password. */
    @NotNull
    @NotBlank
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$") /** Regex for one Uppercase, one Lowercase, and a number */
    @Size(min = 8, max = 32)
    private String password;
    /** The user's first name. */
    @NotNull
    @NotBlank
    private String firstName;
    /** The user's last name. */
    @NotNull
    @NotBlank
    private String lastName;

    /**
     * Gets the username.
     * @return the Username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Gets the email address.
     * @return the Email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets the password.
     * @return the Password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the username.
     * @param username The Username to be set.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Sets the email address.
     * @param email The email address to be set.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Sets the password.
     * @param password The password to be set.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Sets the user's first name.
     * @param firstName The first name to be set.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Sets the user's last name.
     * @param lastName The last name to be set.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the first name.
     * @return the First Name.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Gets the last name.
     * @return the Last Name.
     */
    public String getLastName() {
        return lastName;
    }
}
