package com.djrhodes.ecommercebackend.service;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

/**
 * Service for handling password encryption.
 */
@Service
public class EncryptionService {

    /** The number of salt rounds encryption should run. */
    @Value("${encryption.salt.rounds}")
    private int saltRounds;
    /** The salt built. */
    private String salt;

    /**
     * Post construction method for build salt value.
     */
    @PostConstruct
    public void postConstruct() {
        salt = BCrypt.gensalt(saltRounds);
    }

    /**
     * Encrypts a given password
     * @param password The password to be encrypted.
     * @return The encrypted password.
     */
    public String encryptPassword(String password) {
        return BCrypt.hashpw(password, salt);
    }

    /**
     * Verifies that a password is correct.
     * @param password The password as plain text.
     * @param hash The encrypted password.
     * @return True if the password is correct, else False.
     */
    public boolean verifyPassword(String password, String hash) {
        return BCrypt.checkpw(password, hash);
    }
}
