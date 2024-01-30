package com.companyname.services.core.users.api.model;

import com.companyname.services.core.errorhandling.InvalidRequestException;
import lombok.Getter;

@Getter
public final class UserRegistrationRequest {

    private String firstName;
    private String lastName;
    private String emailAddress;
    private String password;

    public UserRegistrationRequest withFirstName(String firstName) {
        if (firstName == null || firstName.isEmpty()) {
            throw new InvalidRequestException("First name required for new account");
        }
        this.firstName = firstName;
        return this;
    }

    public UserRegistrationRequest withLastName(String lastName) {
        if (lastName == null || lastName.isEmpty()) {
            throw new InvalidRequestException("Last name required for new account");
        }
        this.lastName = lastName;
        return this;
    }

    public UserRegistrationRequest withEmailAddress(String emailAddress) {
        if (emailAddress == null || emailAddress.isEmpty()) {
            throw new InvalidRequestException("Email address required for new account");
        }
        this.emailAddress = emailAddress;
        return this;
    }

    public UserRegistrationRequest withPassword(String password) {
        if (password == null || password.isEmpty()) {
            throw new InvalidRequestException("Password required for new account");
        }
        this.password = password;
        return this;
    }

    public UserRegistrationRequest confirmPassword(String password) {
        if (password == null || password.isEmpty() || !password.equals(this.password)) {
            throw new InvalidRequestException("Passwords do not match for new account");
        }
        return this;
    }
}
