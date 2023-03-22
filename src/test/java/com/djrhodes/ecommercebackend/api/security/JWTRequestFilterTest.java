package com.djrhodes.ecommercebackend.api.security;

import com.djrhodes.ecommercebackend.model.LocalUser;
import com.djrhodes.ecommercebackend.model.repository.LocalUserRepository;
import com.djrhodes.ecommercebackend.service.JWTService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Class for testing the JWTRequestFilter.
 */
@SpringBootTest
@AutoConfigureMockMvc
public class JWTRequestFilterTest {

    /** The Mock MVC. */
    @Autowired
    private MockMvc mvc;
    /** The JWT Service. */
    @Autowired
    private JWTService jwtService;
    /** The LocalUserRepository. */
    @Autowired
    private LocalUserRepository localUserRepository;
    /** The path that should only allow authenticated users. */
    private static final String AUTHENTICATED_PATH = "/auth/me";

    /**
     * Tests that unauthenticated requests are rejected.
     * @throws Exception
     */
    @Test
    public void testUnauthenticatedRequest() throws Exception {
        mvc.perform(get(AUTHENTICATED_PATH)).andExpect(status().is(HttpStatus.FORBIDDEN.value()));
    }

    /**
     * Tests that invalid tokens are rejected.
     * @throws Exception
     */
    @Test
    public void testInvalidToken() throws Exception {
        mvc.perform(get(AUTHENTICATED_PATH).header("Authorization", "InvalidToken"))
                .andExpect(status().is(HttpStatus.FORBIDDEN.value()));
        mvc.perform(get(AUTHENTICATED_PATH).header("Authorization", "Bearer InvalidToken"))
                .andExpect(status().is(HttpStatus.FORBIDDEN.value()));
    }

    /**
     * Tests unverified users who somehow get a jwt are rejected.
     * @throws Exception
     */
    @Test
    public void testUnverifiedUser() throws Exception {
        LocalUser user = localUserRepository.findByUsernameIgnoreCase("UserB").get();
        String token = jwtService.generateJWT(user);
        mvc.perform(get(AUTHENTICATED_PATH).header("Authorization", "Bearer " + token))
                .andExpect(status().is(HttpStatus.FORBIDDEN.value()));
    }

    /**
     * Tests the successful authentication.
     * @throws Exception
     */
    @Test
    public void testSuccessful() throws Exception {
        LocalUser user = localUserRepository.findByUsernameIgnoreCase("UserA").get();
        String token = jwtService.generateJWT(user);
        mvc.perform(get(AUTHENTICATED_PATH).header("Authorization", "Bearer " + token))
                .andExpect(status().is(HttpStatus.OK.value()));
    }

}
