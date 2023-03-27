package com.djrhodes.ecommercebackend.api.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * Request body to reset a password using a password reset token.
 */
public class PasswordResetBody {

    /** The token to authenticate the request. */
    @NotBlank
    @NotNull
    private String token;
    /** The password to set to the account. */
    @NotNull
    @NotBlank
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$") /** Regex for one Uppercase, one Lowercase, and a number */
    @Size(min = 8, max = 32)
    private String password;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
