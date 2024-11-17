package com.menekseyuncu.storemanagement.product.repository;

import com.menekseyuncu.storemanagement.product.model.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    List<ProductEntity> findAllByDeletedAtIsNull();

    Optional<ProductEntity> findByIdAndDeletedAtIsNull(Long id);


}