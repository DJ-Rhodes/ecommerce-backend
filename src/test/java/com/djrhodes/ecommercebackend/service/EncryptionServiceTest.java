package com.djrhodes.ecommercebackend.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Test class to unit test EncryptionService class.
 */
@SpringBootTest
@AutoConfigureMockMvc
public class EncryptionServiceTest {

    /** The Encryption Service. */
    @Autowired
    private EncryptionService encryptionService;

    /**
     * Test for the encryptPassword and verifyPassword methods.
     */
    @Test
    public void testPasswordEncryption() {
        String password = "TestPassword!123";
        String hash = encryptionService.encryptPassword(password);
        Assertions.assertTrue(encryptionService.verifyPassword(password, hash), "Hashed password should match original");
        Assertions.assertFalse(encryptionService.verifyPassword(password + "incorrect", hash), "Altered password should be invalid");
    }

}
