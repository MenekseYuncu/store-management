package com.menekseyuncu.storemanagement.customer.controller.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CustomerGetByEmailRequest(

        @NotBlank
        @Email
        String email
) {
}
