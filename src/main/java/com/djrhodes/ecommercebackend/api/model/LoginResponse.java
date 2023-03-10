package com.djrhodes.ecommercebackend.api.model;


/**
 * The response object sent from log in request.
 */
public class LoginResponse {

    /** The JWT token to be used for authentication. */
    private String jwt;

    /**
     * Sets the JWT token.
     * @param jwt The Token to be set.
     */
    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    /**
     * Gets the JWT Token.
     * @return The JWT Token.
     */
    public String getJwt() {
        return jwt;
    }
}
