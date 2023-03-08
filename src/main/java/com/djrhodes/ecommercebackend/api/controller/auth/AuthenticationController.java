package com.djrhodes.ecommercebackend.api.controller.auth;

import com.djrhodes.ecommercebackend.api.model.RegistrationBody;
import com.djrhodes.ecommercebackend.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest Controller for handling authentication requests.
 */
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    /** The user service. */
    private UserService userService;

    /**
     * Spring injected contructor.
     * @param userService The user service.
     */
    public AuthenticationController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Post Mapping to handle registering of new users.
     * @param registrationBody The registration information of the user.
     */
    @PostMapping("/register")
    public void registerUser(@RequestBody RegistrationBody registrationBody) {
        userService.registerUser(registrationBody);
    }

}
