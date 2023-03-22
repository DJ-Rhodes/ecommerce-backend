package com.djrhodes.ecommercebackend.api.security;

import com.djrhodes.ecommercebackend.model.LocalUser;
import com.djrhodes.ecommercebackend.model.repository.LocalUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service to provide spring with Authentication Principals for unit testing.
 */
@Primary
@Service
public class JUnitUserDetailsService implements UserDetailsService {

    /** The Local User Repository. */
    @Autowired
    private LocalUserRepository localUserRepository;

    /**
     * {@inheritDoc}
     */
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<LocalUser> opUser = localUserRepository.findByUsernameIgnoreCase(username);
        if(opUser.isPresent()) {
            return opUser.get();
        }
        return null;
    }
}
