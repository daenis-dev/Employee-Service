package com.companyname.services.core.accounts.api.model;

import com.companyname.services.core.errorhandling.InvalidRequestException;
import lombok.Getter;

@Getter
public class LoginRequest {

    private String emailAddress;
    private String password;

    public LoginRequest withEmailAddress(String emailAddress) {
        if (emailAddress == null || emailAddress.isEmpty()) {
            throw new InvalidRequestException("Email address required for login");
        }
        this.emailAddress = emailAddress;
        return this;
    }

    public LoginRequest withPassword(String password) {
        if (password == null || password.isEmpty()) {
            throw new InvalidRequestException("Password required for login");
        }
        this.password = password;
        return this;
    }
}
