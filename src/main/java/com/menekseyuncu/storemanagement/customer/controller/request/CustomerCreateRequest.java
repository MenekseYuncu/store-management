package com.menekseyuncu.storemanagement.customer.controller.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record CustomerCreateRequest(

        @NotBlank
        @Size(min = 2, max = 150, message = "Name must be between 2 and 45 characters")
        String name,

        @NotBlank
        @Email(message = "Email should be valid")
        String email,

        @NotBlank
        @Size(max = 300)
        String address,

        @NotBlank
        @Size(min = 6, message = "Password must be at least 6 characters long")
        String password

) {
}

