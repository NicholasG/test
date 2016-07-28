package com.sombra.shop.cart.dao;

import com.sombra.shop.cart.domain.Cart;
import com.sombra.shop.dao.DAO;
import org.springframework.stereotype.Component;

@Component
public class CartDAO implements DAO<Cart> {

    @Override
    public int insert( Cart o ) {
        return 0;
    }

    @Override
    public int update( Cart o ) {
        return 0;
    }

    @Override
    public void delete( Long id ) {

    }

    @Override
    public Cart findOneById( Long id ) {
        return null;
    }

}
