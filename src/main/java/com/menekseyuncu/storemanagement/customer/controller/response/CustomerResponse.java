package com.menekseyuncu.storemanagement.customer.controller.response;


import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record CustomerResponse(
        Long id,
        String name,
        String email,
        String address,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
