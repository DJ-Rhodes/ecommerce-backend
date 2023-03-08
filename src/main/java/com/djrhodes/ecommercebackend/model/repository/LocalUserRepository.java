package com.djrhodes.ecommercebackend.model.repository;

import com.djrhodes.ecommercebackend.model.LocalUser;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * Repository for the localUser data.
 */
public interface LocalUserRepository extends CrudRepository<LocalUser, Long> {
    Optional<LocalUser> findByUsernameIgnoreCase(String username);

    Optional<LocalUser> findByEmailIgnoreCase(String email);

}
