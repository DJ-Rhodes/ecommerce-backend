package com.djrhodes.ecommercebackend.api.model;


/**
 * The response object sent from log in request.
 */
public class LoginResponse {

    /** The JWT token to be used for authentication. */
    private String jwt;
    /** Was the login process successful? */
    private boolean success;
    /** The reason for failure on login attempt. */
    private String failureReason;

    /**
     * Gets whether login attempt was successful or not.
     * @return The login success state.
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * Sets the success of login attempt.
     * @param success
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }

    /**
     * Gets the reason for login failure.
     * @return The failure reason.
     */
    public String getFailureReason() {
        return failureReason;
    }

    /**
     * Sets the reason for failure.
     * @param failureReason The failure reason to sets.
     */
    public void setFailureReason(String failureReason) {
        this.failureReason = failureReason;
    }

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
