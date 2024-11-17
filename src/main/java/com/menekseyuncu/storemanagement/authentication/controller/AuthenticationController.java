package com.menekseyuncu.storemanagement.authentication.controller;


import com.menekseyuncu.storemanagement.authentication.controller.request.ChangePasswordRequest;
import com.menekseyuncu.storemanagement.authentication.controller.request.LoginRequest;
import com.menekseyuncu.storemanagement.authentication.service.AuthenticationService;
import com.menekseyuncu.storemanagement.common.responses.BaseResponse;
import com.menekseyuncu.storemanagement.customer.controller.request.CustomerCreateRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public BaseResponse<Void> registerCustomer(
            @Valid @RequestBody final CustomerCreateRequest customerCreateRequest
    ) {
        authenticationService.register(customerCreateRequest);
        return BaseResponse.SUCCESS;
    }

    @PostMapping("/login")
    public BaseResponse<Void> loginCustomer(
            @Valid @RequestBody final LoginRequest loginRequest
    ) {
        authenticationService.login(loginRequest);
        return BaseResponse.SUCCESS;
    }

    @PutMapping("/password")
    public BaseResponse<Void> changePassword(
            @Valid @RequestBody final ChangePasswordRequest changePasswordRequest) {
        authenticationService.changePassword(changePasswordRequest);
        return BaseResponse.SUCCESS;
    }

}
