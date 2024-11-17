package com.menekseyuncu.storemanagement.cart.service.impl;

import com.menekseyuncu.storemanagement.cart.controller.request.CartCreateRequest;
import com.menekseyuncu.storemanagement.cart.exceptions.CartAlreadyDeletedException;
import com.menekseyuncu.storemanagement.cart.exceptions.CartNotFoundException;
import com.menekseyuncu.storemanagement.cart.model.domain.Cart;
import com.menekseyuncu.storemanagement.cart.model.entity.CartEntity;
import com.menekseyuncu.storemanagement.cart.model.mapper.CartCreateRequestToEntityMapper;
import com.menekseyuncu.storemanagement.cart.model.mapper.CartEntityToDomainMapper;
import com.menekseyuncu.storemanagement.cart.repository.CartItemRepository;
import com.menekseyuncu.storemanagement.cart.repository.CartRepository;
import com.menekseyuncu.storemanagement.cart.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
class CartServiceImpl implements CartService {

    private static final CartCreateRequestToEntityMapper cartCreateRequestToEntityMapper = CartCreateRequestToEntityMapper.INSTANCE;
    private static final CartEntityToDomainMapper cartEntityToDomainMapper = CartEntityToDomainMapper.INSTANCE;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    @Override
    public void createCart(CartCreateRequest cartRequest) {

        CartEntity cart = cartCreateRequestToEntityMapper.map(cartRequest);
        cart.setTotalPrice(BigDecimal.ZERO);
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
