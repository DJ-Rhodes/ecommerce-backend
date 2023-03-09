package com.djrhodes.ecommercebackend.service;

import com.djrhodes.ecommercebackend.api.model.RegistrationBody;
import com.djrhodes.ecommercebackend.exception.UserAlreadyExistsException;
import com.djrhodes.ecommercebackend.model.LocalUser;
import com.djrhodes.ecommercebackend.model.repository.LocalUserRepository;
import org.springframework.stereotype.Service;

/**
 * Service for handling user actions.
 */
@Service
public class UserService {

    /** The localUserRepository. */
    private LocalUserRepository localUserRepository;
    private EncryptionService encryptionService;

    /**
     * Spring injected constructor.
     *
     * @param localUserRepository The localUser Repository.
     * @param encryptionService
     */
    public UserService(LocalUserRepository localUserRepository, EncryptionService encryptionService) {
        this.localUserRepository = localUserRepository;
        this.encryptionService = encryptionService;
    }

    /**
     * Registers a user with the provided information.
     * @param registrationBody The registration information.
     * @return The localUser that has been written to the database.
     * @hrows UserAlreadyExistsException thrown if a user already exists with given username or email.
     */
    public LocalUser registerUser(RegistrationBody registrationBody) throws UserAlreadyExistsException {
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
        return localUserRepository.save(user);
    }

}
