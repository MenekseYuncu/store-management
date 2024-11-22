package com.menekseyuncu.storemanagement.cart.controller.request;

import jakarta.validation.constraints.NotNull;

public record CartCreateRequest(

        @NotNull
        Long customerId
) {
}
