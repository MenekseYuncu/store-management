package com.menekseyuncu.storemanagement.customer.repository;

import com.menekseyuncu.storemanagement.customer.model.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

    Optional<CustomerEntity> findByEmailAndDeletedAtIsNull(String email);

    Optional<CustomerEntity> findByIdAndDeletedAtIsNull(Long id);

    List<CustomerEntity> findAllByDeletedAtIsNull();
}