package com.sombra.shop.cart.service;

import com.sombra.shop.cart.domain.Cart;

public interface CartService {

    int add( Cart cart );

    Cart getOne( Long id );

    int addGoodToCart( Long goodId, Long cartId );

    void deleteGoodFromCart( Long goodId, Long cartId );

}
