package com.djrhodes.ecommercebackend.api.security;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.djrhodes.ecommercebackend.model.LocalUser;
import com.djrhodes.ecommercebackend.model.repository.LocalUserRepository;
import com.djrhodes.ecommercebackend.service.JWTService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

/**
 * Filter for decoding a JWT in the Authorization header and loading the user
 * object into the authentication context.
 */
@Component
public class JWTRequestFilter extends OncePerRequestFilter {

    /** The JWT Service. */
    private JWTService jwtService;
    /** The Local User Repository. */
    private LocalUserRepository localUserRepository;

    /**
     * Constructor for spring injection.
     * @param jwtService
     * @param localUserRepository
     */
    public JWTRequestFilter(JWTService jwtService, LocalUserRepository localUserRepository) {
        this.jwtService = jwtService;
        this.localUserRepository = localUserRepository;
    }

    /**
     * Builds authentication token for from Servlet Request.
     * @param request The HTTP Servlet Request
     * @param response The HTTP Servlet Response
     * @param filterChain The Filter Chain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String tokenHeader = request.getHeader("Authorization");
        if(tokenHeader != null && tokenHeader.startsWith("Bearer ")) {
            String token = tokenHeader.substring(7);
            try {
                String username = jwtService.getUsername(token);
                Optional<LocalUser> opUser = localUserRepository.findByUsernameIgnoreCase(username);
                if(opUser.isPresent()) {
                    LocalUser user = opUser.get();
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            } catch (JWTDecodeException exception) {
            }
        }
        filterChain.doFilter(request, response);
    }
}
