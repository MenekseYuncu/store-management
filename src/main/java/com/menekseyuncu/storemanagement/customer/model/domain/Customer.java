package com.menekseyuncu.storemanagement.customer.model.domain;

import java.time.LocalDateTime;

public record Customer(

        Long id,
        String name,
        String email,
        String address,
        LocalDateTime createdAt,
        LocalDateTime updatedAt

) {
}
