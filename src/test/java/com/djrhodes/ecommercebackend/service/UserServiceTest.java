package com.djrhodes.ecommercebackend.service;

import com.djrhodes.ecommercebackend.api.model.LoginBody;
import com.djrhodes.ecommercebackend.api.model.RegistrationBody;
import com.djrhodes.ecommercebackend.exception.EmailFailureException;
import com.djrhodes.ecommercebackend.exception.UserAlreadyExistsException;
import com.djrhodes.ecommercebackend.exception.UserNotVerifiedException;
import com.djrhodes.ecommercebackend.model.VerificationToken;
import com.djrhodes.ecommercebackend.model.repository.VerificationTokenRepository;
import com.icegreen.greenmail.configuration.GreenMailConfiguration;
import com.icegreen.greenmail.junit5.GreenMailExtension;
import com.icegreen.greenmail.util.ServerSetupTest;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * Test class to unit test the UserService class.
 */
@SpringBootTest
public class UserServiceTest {

    /** Extension for mocking email sending. */
    @RegisterExtension
    private static GreenMailExtension greenMailExtension = new GreenMailExtension(ServerSetupTest.SMTP)
            .withConfiguration(GreenMailConfiguration.aConfig().withUser("springboot", "secret"))
            .withPerMethodLifecycle(true);
    /** The UserService to test. */
    @Autowired
    private UserService userService;
    /** The Verification Token Repository. */
    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

    /**
     * Tests the registration process of the user.
     * @throws MessagingException Thrown if the mocked email service fails.
     */
    @Test
    @Transactional
    public void testRegisterUser() throws MessagingException {
        RegistrationBody registrationBody = new RegistrationBody();
        registrationBody.setUsername("UserA");
        registrationBody.setEmail("UserServiceTest$testRegisterUser@junit.com");
        registrationBody.setFirstName("FirstName");
        registrationBody.setLastName("LastName");
        registrationBody.setPassword("MySecretPassword123");
        Assertions.assertThrows(UserAlreadyExistsException.class,
                () -> userService.registerUser(registrationBody), "Username should already be in use.");
        registrationBody.setUsername("UserServiceTest$test");
        registrationBody.setEmail("UserA@junit.com");
        Assertions.assertThrows(UserAlreadyExistsException.class,
                () -> userService.registerUser(registrationBody), "Email should already be in use.");
        registrationBody.setEmail("UserServiceTest$testRegisterUser@junit.com");
        Assertions.assertDoesNotThrow(() -> userService.registerUser(registrationBody),
                "User should register successfully.");
        Assertions.assertEquals(registrationBody.getEmail(), greenMailExtension.getReceivedMessages()[0]
                .getRecipients(Message.RecipientType.TO)[0].toString());
    }

    /**
     * Tests the loginUser method.
     * @throws UserNotVerifiedException
     * @throws EmailFailureException
     */
    @Test
    @Transactional
    public void testLoginUser() throws UserNotVerifiedException, EmailFailureException {
        LoginBody loginBody = new LoginBody();
        loginBody.setUsername("UserA-NotExists");
        loginBody.setPassword("PasswordA123");
        Assertions.assertNull(userService.loginUser(loginBody), "User should not exist.");
        loginBody.setUsername("UserA");
        loginBody.setPassword("WrongPassword");
        Assertions.assertNull(userService.loginUser(loginBody), "Password should be incorrect.");
        loginBody.setPassword("PasswordA123");
        Assertions.assertNotNull(userService.loginUser(loginBody), "User should login successfully.");
        loginBody.setUsername("UserB");
        loginBody.setPassword("PasswordB123");
        try {
            userService.loginUser(loginBody);
            Assertions.assertTrue(false, "User should not have verified email.");
        } catch (UserNotVerifiedException e) {
            Assertions.assertTrue(e.isNewEmailSent(), "Verification email should be sent.");
            Assertions.assertEquals(1, greenMailExtension.getReceivedMessages().length);
        }
        try {
            userService.loginUser(loginBody);
            Assertions.assertTrue(false, "User should not have verified email.");
        } catch (UserNotVerifiedException e) {
            Assertions.assertFalse(e.isNewEmailSent(), "Verification email should not be resent.");
            Assertions.assertEquals(1, greenMailExtension.getReceivedMessages().length);
        }
    }

    /**
     * Tests the verifyUser method.
     * @throws EmailFailureException
     */
    @Test
    @Transactional
    public void testVerifyUser() throws EmailFailureException {
        Assertions.assertFalse(userService.verifyUser("Invalid Token"), "Token is invalid, or does not exist.");
        LoginBody loginBody = new LoginBody();
        loginBody.setUsername("UserB");
        loginBody.setPassword("PasswordB123");
        try {
            userService.loginUser(loginBody);
            Assertions.assertTrue(false, "User should not have verified email.");
        } catch (UserNotVerifiedException e) {
            List<VerificationToken> tokenList = verificationTokenRepository.findByUser_IdOrderByIdDesc(2L);
            String token = tokenList.get(0).getToken();
            Assertions.assertTrue(userService.verifyUser(token), "Token should be valid.");
            Assertions.assertNotNull(loginBody, "User should be verified.");
        }
    }

}
