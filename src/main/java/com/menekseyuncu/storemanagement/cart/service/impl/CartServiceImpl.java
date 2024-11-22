package com.menekseyuncu.storemanagement.cart.service.impl;

import com.menekseyuncu.storemanagement.cart.controller.request.CartCreateRequest;
import com.menekseyuncu.storemanagement.cart.exceptions.CartAlreadyDeletedException;
import com.menekseyuncu.storemanagement.cart.exceptions.CartNotFoundException;
import com.menekseyuncu.storemanagement.cart.model.domain.Cart;
import com.menekseyuncu.storemanagement.cart.model.entity.CartEntity;
import com.menekseyuncu.storemanagement.cart.model.mapper.CartEntityToDomainMapper;
import com.menekseyuncu.storemanagement.cart.repository.CartItemRepository;
import com.menekseyuncu.storemanagement.cart.repository.CartRepository;
import com.menekseyuncu.storemanagement.cart.service.CartService;
import com.menekseyuncu.storemanagement.customer.exceptions.CustomerNotFoundException;
import com.menekseyuncu.storemanagement.customer.model.entity.CustomerEntity;
import com.menekseyuncu.storemanagement.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
class CartServiceImpl implements CartService {

    private static final CartEntityToDomainMapper cartEntityToDomainMapper = CartEntityToDomainMapper.INSTANCE;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final CustomerRepository customerRepository;

    @Override
    public void createCart(CartCreateRequest cartRequest) {
        CustomerEntity customer = customerRepository.findById(cartRequest.customerId())
                .orElseThrow(CustomerNotFoundException::new);

        CartEntity cart = CartEntity.builder()
                .customer(customer)
                .totalPrice(BigDecimal.ZERO)
                .build();

        cartRepository.save(cart);
    }

    @Override
    public Cart getCartByCustomerId(Long customerId) {
        CartEntity cartEntity = cartRepository.findByCustomerIdAndDeletedAtIsNull(customerId)
                .orElseThrow(CartNotFoundException::new);

        cartEntity.getCartItems().listIterator();

        return cartEntityToDomainMapper.map(cartEntity.toCart());
    }

    @Override
    public void emptyCart(Long id) {
        CartEntity cartEntity = cartRepository.findByIdAndDeletedAtIsNull(id)
                .orElseThrow(CartNotFoundException::new);

        if (cartEntity != null) {

            cartItemRepository.deleteAll(cartEntity.getCartItems());
            cartEntity.getCartItems().clear();

            cartEntity.setTotalPrice(BigDecimal.ZERO);

            cartRepository.save(cartEntity);
        } else {
            throw new CartNotFoundException();
        }
    }

    @Override
    public void deleteCart(Long id) {
        CartEntity cartEntity = cartRepository.findByIdAndDeletedAtIsNull(id)
                .orElseThrow(CartNotFoundException::new);

        if (cartEntity.isDeleted()) {
            throw new CartAlreadyDeletedException();
        }

        cartEntity.softDelete();
        cartRepository.save(cartEntity);
    }
}
