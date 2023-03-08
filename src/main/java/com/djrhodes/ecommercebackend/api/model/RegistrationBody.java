package com.djrhodes.ecommercebackend.api.model;

/**
 * The information require to register a new user.
 */
public class RegistrationBody {

    /** The username. */
    private String username;
    /** The user's email address. */
    private String email;
    /** The user's password. */
    private String password;
    /** The user's first name. */
    private String firstName;
    /** The user's last name. */
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
     * Gets the passwword.
     * @return the Password.
     */
    public String getPassword() {
        return password;
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
