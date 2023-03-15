package com.djrhodes.ecommercebackend.service;

import com.djrhodes.ecommercebackend.api.model.LoginBody;
import com.djrhodes.ecommercebackend.api.model.RegistrationBody;
import com.djrhodes.ecommercebackend.exception.EmailFailureException;
import com.djrhodes.ecommercebackend.exception.UserAlreadyExistsException;
import com.djrhodes.ecommercebackend.model.LocalUser;
import com.djrhodes.ecommercebackend.model.VerificationToken;
import com.djrhodes.ecommercebackend.model.repository.LocalUserRepository;
import com.djrhodes.ecommercebackend.model.repository.VerificationTokenRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;

/**
 * Service for handling user actions.
 */
@Service
public class UserService {

    /** The localUserRepository. */
    private LocalUserRepository localUserRepository;
    /** The encryption service. */
    private EncryptionService encryptionService;
    /** The JWT service. */
    private JWTService jwtService;
    /** The email service. */
    private EmailService emailService;
    /** The VerificationTokenDAO. */
    private VerificationTokenRepository verificationTokenRepository;

    /**
     * Spring injected constructor.
     *
     * @param localUserRepository         The localUser Repository.
     * @param encryptionService
     * @param jwtService
     * @param emailService
     * @param verificationTokenRepository
     */
    public UserService(LocalUserRepository localUserRepository, EncryptionService encryptionService, JWTService jwtService,
                       EmailService emailService, VerificationTokenRepository verificationTokenRepository) {
        this.localUserRepository = localUserRepository;
        this.encryptionService = encryptionService;
        this.jwtService = jwtService;
        this.emailService = emailService;
        this.verificationTokenRepository = verificationTokenRepository;
    }

    /**
     * Registers a user with the provided information.
     * @param registrationBody The registration information.
     * @return The localUser that has been written to the database.
     * @hrows UserAlreadyExistsException thrown if a user already exists with given username or email.
     */
    public LocalUser registerUser(RegistrationBody registrationBody) throws UserAlreadyExistsException, EmailFailureException {
        if (localUserRepository.findByEmailIgnoreCase(registrationBody.getEmail()).isPresent()
            || localUserRepository.findByUsernameIgnoreCase(registrationBody.getUsername()).isPresent()) {
            throw new UserAlreadyExistsException();
        }
        LocalUser user = new LocalUser();
        user.setUsername(registrationBody.getUsername());
        user.setEmail(registrationBody.getEmail());
        user.setFirstName(registrationBody.getFirstName());
        user.setLastName(registrationBody.getLastName());
        user.setPassword(encryptionService.encryptPassword(registrationBody.getPassword()));
        VerificationToken verificationToken = createVerificationToken(user);
        emailService.sendVerificationEmail(verificationToken);
        return localUserRepository.save(user);
    }

    /**
     * Creates a VerificationToken object for sending to the user.
     * @param user The user the token is being generated for.
     * @return The object created.
     */
    private VerificationToken createVerificationToken(LocalUser user) {
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(jwtService.generateVerificationJWT(user));
        verificationToken.setCreatedTimestamp(new Timestamp(System.currentTimeMillis()));
        verificationToken.setUser(user);
        user.getVerificationTokens().add(verificationToken);
        return verificationToken;
    }

    /**
     * Logs in a user and provides an authentication token back.
     * @param loginBody The login request.
     * @return The authentication token. Null if the request was invalid.
     */
    public String loginUser(LoginBody loginBody) {
        Optional<LocalUser> optionalUser = localUserRepository.findByUsernameIgnoreCase(loginBody.getUsername());
        if (optionalUser.isPresent()) {
            LocalUser user = optionalUser.get();
            if (encryptionService.verifyPassword(loginBody.getPassword(), user.getPassword())) {
                return jwtService.generateJWT(user);
            }
        }
        return null;
    }

}
