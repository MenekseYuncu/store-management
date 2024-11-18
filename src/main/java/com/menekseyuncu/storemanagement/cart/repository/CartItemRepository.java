package com.menekseyuncu.storemanagement.cart.repository;

import com.menekseyuncu.storemanagement.cart.model.entity.CartItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface CartItemRepository extends JpaRepository<CartItemEntity, Long> {

    Optional<CartItemEntity> findByIdAndDeletedAtIsNull(Long id);

    Optional<CartItemEntity> findByCartIdAndProductIdAndDeletedAtIsNull(Long cartId, Long produtId);

    List<CartItemEntity> findAllByCartIdAndDeletedAtIsNull(Long cartId);
}
