package com.djrhodes.ecommercebackend.service;

import com.djrhodes.ecommercebackend.api.model.RegistrationBody;
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

    /**
     * Spring injected constructor.
     * @param localUserRepository The localUser Repository.
     */
    public UserService(LocalUserRepository localUserRepository) {
        this.localUserRepository = localUserRepository;
    }

    /**
     * Registers a user with the provided information.
     * @param registrationBody The registration information.
     * @return The localUser that has been written to the database.
     */
    public LocalUser registerUser(RegistrationBody registrationBody) {
        LocalUser user = new LocalUser();
        user.setUsername(registrationBody.getUsername());
        user.setEmail(registrationBody.getEmail());
        user.setFirstName(registrationBody.getFirstName());
        user.setLastName(registrationBody.getLastName());
        //TODO: Encrypt passwords
        user.setPassword(registrationBody.getPassword());
        return localUserRepository.save(user);
    }

}
