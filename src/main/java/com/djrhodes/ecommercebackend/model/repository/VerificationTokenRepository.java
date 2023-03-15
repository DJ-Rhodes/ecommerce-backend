package com.djrhodes.ecommercebackend.model.repository;

import com.djrhodes.ecommercebackend.model.VerificationToken;
import org.springframework.data.repository.ListCrudRepository;

/**
 * Repository for the VerificationToken data.
 */
public interface VerificationTokenRepository extends ListCrudRepository<VerificationToken, Long> {
}
