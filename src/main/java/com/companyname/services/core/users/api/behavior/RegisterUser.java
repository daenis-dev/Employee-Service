package com.companyname.services.core.users.api.behavior;

import com.companyname.services.core.users.api.model.UserRegistrationRequest;

public interface RegisterUser {

    void forRequest(UserRegistrationRequest request);
}
