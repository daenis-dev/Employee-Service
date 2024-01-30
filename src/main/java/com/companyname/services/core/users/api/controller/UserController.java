package com.companyname.services.core.users.api.controller;

import com.companyname.services.core.users.api.behavior.RegisterUser;
import com.companyname.services.core.users.api.model.UserRegistrationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final RegisterUser registerUser;

    @PostMapping("/v1/users")
    public ResponseEntity<?> registerUser(@RequestParam("first-name") String firstName, @RequestParam("last-name") String lastName, @RequestParam("email-address") String emailAddress, @RequestParam("password") String password, @RequestParam("confirmed-password") String confirmedPassword) {
        registerUser.forRequest(new UserRegistrationRequest()
                .withFirstName(firstName)
                .withLastName(lastName)
                .withEmailAddress(emailAddress)
                .withPassword(password)
                .confirmPassword(confirmedPassword));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
