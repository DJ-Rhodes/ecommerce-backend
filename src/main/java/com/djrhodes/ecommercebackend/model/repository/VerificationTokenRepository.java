package com.djrhodes.ecommercebackend.model.repository;

import com.djrhodes.ecommercebackend.model.LocalUser;
import com.djrhodes.ecommercebackend.model.VerificationToken;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;
import java.util.Optional;

/**
 * Repository for the VerificationToken data.
 */
public interface VerificationTokenRepository extends ListCrudRepository<VerificationToken, Long> {
    List<VerificationToken> findByUser_IdOrderByIdDesc(Long id);
    Optional<VerificationToken> findByToken(String token);

    void deleteByUser(LocalUser user);
}
