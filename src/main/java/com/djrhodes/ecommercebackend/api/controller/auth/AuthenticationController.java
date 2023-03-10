package com.djrhodes.ecommercebackend.api.controller.auth;

import com.djrhodes.ecommercebackend.api.model.LoginBody;
import com.djrhodes.ecommercebackend.api.model.LoginResponse;
import com.djrhodes.ecommercebackend.api.model.RegistrationBody;

import com.djrhodes.ecommercebackend.exception.UserAlreadyExistsException;
import com.djrhodes.ecommercebackend.model.LocalUser;
import com.djrhodes.ecommercebackend.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

/**
 * Rest Controller for handling authentication requests.
 */
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    /** The user service. */
    private UserService userService;

    /**
     * Spring injected constructor.
     * @param userService The user service.
     */
    public AuthenticationController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Post Mapping to handle registering of new users.
     * @param registrationBody The registration information of the user.
     * @return Response to front-end.
     */
    @PostMapping("/register")
    public ResponseEntity registerUser(@Valid @RequestBody RegistrationBody registrationBody){
        try {
            userService.registerUser(registrationBody);
            return ResponseEntity.ok().build();
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    /**
     * Post Mapping to handle user logins to provide authentication token.
     * @param loginBody The login information.
     * @return The authentication token if successful.
     */
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginUser(@Valid @RequestBody LoginBody loginBody){
        String jwt = userService.loginUser(loginBody);
        if (jwt == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } else {
            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setJwt(jwt);
            return ResponseEntity.ok(loginResponse);
        }

    }

    /**
     * Gets the profile of the currently logged-in user.
     * @param user The authentication principal object.
     * @return The user profile.
     */
    @GetMapping("/me")
    public LocalUser getLoggedInUserProfile(@AuthenticationPrincipal LocalUser user) {
        return user;
    }

}
