package com.menekseyuncu.storemanagement.cart.service.impl;

import com.menekseyuncu.storemanagement.cart.controller.request.CartItemChangeQuantityRequest;
import com.menekseyuncu.storemanagement.cart.controller.request.CartItemRequest;
import com.menekseyuncu.storemanagement.cart.controller.response.CartItemResponse;
import com.menekseyuncu.storemanagement.cart.exceptions.CartAlreadyDeletedException;
import com.menekseyuncu.storemanagement.cart.exceptions.CartItemNotFoundException;
import com.menekseyuncu.storemanagement.cart.model.entity.CartEntity;
import com.menekseyuncu.storemanagement.cart.model.entity.CartItemEntity;
import com.menekseyuncu.storemanagement.cart.model.mapper.CartItemEntityToResponseMapper;
import com.menekseyuncu.storemanagement.cart.model.mapper.CartItemRequestToCartItemEntityMapper;
import com.menekseyuncu.storemanagement.cart.repository.CartItemRepository;
import com.menekseyuncu.storemanagement.cart.repository.CartRepository;
import com.menekseyuncu.storemanagement.cart.service.CartItemService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
class CartItemServiceImpl implements CartItemService {

    private static final CartItemRequestToCartItemEntityMapper cartItemEntityToRequestMapper = CartItemRequestToCartItemEntityMapper.INSTANCE;
    private static final CartItemEntityToResponseMapper cartItemEntityToResponseMapper = CartItemEntityToResponseMapper.INSTANCE;
    private final CartItemRepository cartItemRepository;
    private final CartRepository cartRepository;


    @Override
    public void addCartItem(CartItemRequest cartItemRequest) {
        Optional<CartItemEntity> existingCartItem = cartItemRepository
                .findByCartIdAndProductIdAndDeletedAtIsNull(cartItemRequest.cartId(), cartItemRequest.productId());

        if (existingCartItem.isPresent()) {
            CartItemEntity cartItem = existingCartItem.get();
            cartItem.setQuantity(cartItem.getQuantity() + cartItemRequest.quantity());
            cartItemRepository.save(cartItem);
            return;
        }
        CartItemEntity cartItemEntity = cartItemEntityToRequestMapper.map(cartItemRequest);
        cartItemRepository.save(cartItemEntity);
    }

    @Override
    public CartItemResponse getCartItemById(Long id) {
        CartItemEntity cartItemEntity = cartItemRepository.findById(id)
                .orElseThrow(CartItemNotFoundException::new);

        return cartItemEntityToResponseMapper.map(cartItemEntity);
    }

    @Override
    public void updateCartItemQuantity(Long itemId, CartItemChangeQuantityRequest changeQuantityRequest) {
        CartItemEntity cartItem = cartItemRepository.findByIdAndDeletedAtIsNull(itemId)
                .orElseThrow(CartItemNotFoundException::new);

        Long quantity = changeQuantityRequest.quantity();

        cartItem.setQuantity(quantity);
        cartItemRepository.save(cartItem);
    }

    @Override
    public List<CartItemResponse> getCartItemsByCartId(Long cartId) {
        List<CartItemEntity> cartItems = cartItemRepository.findAllByCartIdAndDeletedAtIsNull(cartId);

        return cartItemEntityToResponseMapper.map(cartItems);
    }

    @Override
    @Transactional
    public void removeCartItem(Long id) {
        CartItemEntity cartItem = cartItemRepository.findByIdAndDeletedAtIsNull(id)
                .orElseThrow(CartItemNotFoundException::new);

        if (cartItem.isDeleted()) {
            throw new CartAlreadyDeletedException();
        }

        cartItem.softDelete();
        cartItemRepository.save(cartItem);

        CartEntity cart = cartItem.getCart();
        cart.getCartItems().removeIf(item -> item.getId().equals(id));
        cart.setTotalPrice(calculateTotalPrice(cart));
        cartRepository.save(cart);
    }

    private BigDecimal calculateTotalPrice(CartEntity cart) {
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (CartItemEntity item : cart.getCartItems()) {
            totalPrice = totalPrice.add(item.getPrice().multiply(new BigDecimal(item.getQuantity())));
        }
        return totalPrice;
    }
}
