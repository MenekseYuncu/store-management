package com.menekseyuncu.storemanagement.cart.controller;


import com.menekseyuncu.storemanagement.cart.controller.request.CartCreateRequest;
import com.menekseyuncu.storemanagement.cart.controller.response.CartResponse;
import com.menekseyuncu.storemanagement.cart.model.domain.Cart;
import com.menekseyuncu.storemanagement.cart.model.mapper.CartToCartResponseMapper;
import com.menekseyuncu.storemanagement.cart.service.CartService;
import com.menekseyuncu.storemanagement.common.responses.BaseResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cart")
class CartController {

    private static final CartToCartResponseMapper cartToCartResponse = CartToCartResponseMapper.INSTANCE;
    private final CartService cartService;

    @GetMapping("/{customerId}")
    public BaseResponse<CartResponse> getCartByCustomerId(
            @PathVariable @Positive Long customerId
    ) {
        Cart cart = cartService.getCartByCustomerId(customerId);
        CartResponse cartResponse = cartToCartResponse.map(cart);
        return BaseResponse.successOf(cartResponse);
    }

    @PostMapping
    public BaseResponse<Void> createCart(
            @RequestBody @Valid final CartCreateRequest cartRequest
    ) {
        cartService.createCart(cartRequest);
        return BaseResponse.SUCCESS;
    }

    @PostMapping("/{id}/empty")
    public ResponseEntity<BaseResponse<Void>> emptyCart(
            @PathVariable @Positive Long id
    ) {
        cartService.emptyCart(id);
        return ResponseEntity.ok(BaseResponse.SUCCESS);
    }

    @DeleteMapping("/{id}")
    public BaseResponse<Void> deleteCart(
            @PathVariable @Positive final Long id) {
        cartService.deleteCart(id);
        return BaseResponse.SUCCESS;
    }
}

