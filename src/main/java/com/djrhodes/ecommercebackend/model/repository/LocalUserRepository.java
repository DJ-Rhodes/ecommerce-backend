package com.djrhodes.ecommercebackend.model.repository;

import com.djrhodes.ecommercebackend.model.LocalUser;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository for the localUser data.
 */
public interface LocalUserRepository extends CrudRepository<LocalUser, Long> {


}
