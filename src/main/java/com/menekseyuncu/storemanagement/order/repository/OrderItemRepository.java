package com.menekseyuncu.storemanagement.order.repository;

import com.menekseyuncu.storemanagement.order.model.entity.OrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItemEntity, Long> {

    Optional<OrderItemEntity> findByOrderIdAndProductIdAndDeletedAtIsNull(Long orderId, Long productId);



    Optional<OrderItemEntity> findByOrderIdAndDeletedAtIsNull(Long orderId);

    List<OrderItemEntity> findAllByDeletedAtIsNull();

    Optional<OrderItemEntity> findByIdAndDeletedAtIsNull(Long id);

}
