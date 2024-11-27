package com.menekseyuncu.storemanagement.order.repository;

import com.menekseyuncu.storemanagement.order.model.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    List<OrderEntity> findByCustomerIdAndDeletedAtIsNull(Long customerId);

    Optional<OrderEntity> findByIdAndDeletedAtIsNull(Long id);

}