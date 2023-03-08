package com.djrhodes.ecommercebackend.api.controller.auth;

import com.djrhodes.ecommercebackend.api.model.RegistrationBody;

import com.djrhodes.ecommercebackend.exception.UserAlreadyExistsException;
import com.djrhodes.ecommercebackend.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

}
