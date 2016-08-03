package com.sombra.shop.cart.mapper;

import com.sombra.shop.cart.domain.Cart;
import com.sombra.shop.good.domain.Good;
import com.sombra.shop.good.service.GoodService;
import com.sombra.shop.user.domain.CustomUser;
import com.sombra.shop.user.service.CustomUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;

@Component
public class CartRowMapper implements RowMapper<Cart> {

    private static final String USER_ID = "user_id";
    private static final String AMOUNT = "amount";

    private final CustomUserService userService;

    private final GoodService goodService;

    @Autowired
    public CartRowMapper( CustomUserService userService, GoodService goodService ) {
        Assert.notNull( userService, "userService must not be null" );
        Assert.notNull( goodService, "goodService must not be null" );
        this.goodService = goodService;
        this.userService = userService;
    }

    @Override
    public Cart mapRow( ResultSet resultSet, int i ) throws SQLException {
        return getCart( resultSet );
    }

    private Cart getCart( ResultSet resultSet ) throws SQLException {
        Cart cart = new Cart();
        cart.setId( resultSet.getLong( USER_ID ) );
        cart.setAmount( resultSet.getInt( AMOUNT ) );

        CustomUser user = userService.getOne( cart.getId() );
        cart.setUser( user );

        List<Good> goods = goodService.getAllByCartId( cart.getId() );
        cart.setGoods( new HashSet<>( goods ) );

        return cart;
    }

}
