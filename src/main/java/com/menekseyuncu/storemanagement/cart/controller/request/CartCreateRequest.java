package com.menekseyuncu.storemanagement.cart.controller.request;

import jakarta.validation.constraints.NotBlank;

public record CartCreateRequest(

        @NotBlank
        Long customerId
) {
}
