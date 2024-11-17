package com.menekseyuncu.storemanagement.authentication.service;

import com.menekseyuncu.storemanagement.authentication.controller.request.ChangePasswordRequest;
import com.menekseyuncu.storemanagement.authentication.controller.request.LoginRequest;
import com.menekseyuncu.storemanagement.customer.controller.request.CustomerCreateRequest;

public interface AuthenticationService {

    void register(CustomerCreateRequest customerCreateRequest);

    void login(LoginRequest loginRequest);

    void changePassword(ChangePasswordRequest changePasswordRequest);

}
