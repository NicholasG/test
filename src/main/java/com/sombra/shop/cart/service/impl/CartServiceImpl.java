package com.sombra.shop.cart.service.impl;

import com.sombra.shop.cart.dao.CartDAO;
import com.sombra.shop.cart.domain.Cart;
import com.sombra.shop.cart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class CartServiceImpl implements CartService {

    private final CartDAO cartDAO;

    @Autowired
    public CartServiceImpl( CartDAO cartDAO ) {
        Assert.notNull( cartDAO, "cartDAO must not be null" );
        this.cartDAO = cartDAO;
    }

    @Override
    public int add( Cart cart ) {
        return cartDAO.insert( cart );
    }

    @Override
    public Cart getOne( Long id ) {
        return cartDAO.findOneById( id );
    }

    @Override
    public int addGoodToCart( Long goodId, Long cartId ) {
        return cartDAO.addGoodToCart( goodId, cartId );
    }

    @Override
    public void deleteGoodFromCart( Long goodId, Long cartId ) {
        cartDAO.deleteGoodFromCart( goodId, cartId );
    }

}
