package com.menekseyuncu.storemanagement.authentication.controller.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ChangePasswordRequest(

        @NotBlank
        @Email
        String email,

        @NotBlank
        @Size(min = 6, message = "Password must be at least 6 characters long")
        String oldPassword,

        @NotBlank
        @Size(min = 6, message = "Password must be at least 6 characters long")
        String newPassword

) {
}
