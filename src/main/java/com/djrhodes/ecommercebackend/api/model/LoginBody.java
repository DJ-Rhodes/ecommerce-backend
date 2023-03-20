package com.djrhodes.ecommercebackend.api.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * The body for the login requests.
 */
public class LoginBody {

    /** The username to log in with. */
    @NotNull
    @NotBlank
    private String username;
    /** The password to log in with. */
    @NotNull
    @NotBlank
    private String password;

    /**
     * Gets the username being logged in.
     * @return The username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the Username.
     * @param username The username to be set.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Sets the Password.
     * @param password The password to be set.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the password being used to log in.
     * @return The password.
     */
    public String getPassword() {
        return password;
    }
}
