package com.menekseyuncu.storemanagement.order.controller.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;


@Builder
public record OrderRequest(

        @NotBlank
        String customerId
) {
}
