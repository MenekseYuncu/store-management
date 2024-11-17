package com.menekseyuncu.storemanagement.cart.repository;

import com.menekseyuncu.storemanagement.cart.model.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<CartEntity, Long> {

    Optional<CartEntity> findByCustomerIdAndDeletedAtIsNull(Long customerId);

    Optional<CartEntity> findByIdAndDeletedAtIsNull(Long cartId);

}
