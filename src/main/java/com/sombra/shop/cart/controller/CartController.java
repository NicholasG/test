package com.sombra.shop.cart.controller;

import com.sombra.shop.cart.dao.CartDAO;
import com.sombra.shop.cart.domain.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( value = "/carts" )
public class CartController {

    private final CartDAO cartDAO;

    @Autowired
    public CartController( CartDAO cartDAO ) {
        this.cartDAO = cartDAO;
    }

    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.GET
    )
    public ResponseEntity<Cart> getOne( @PathVariable( "id" ) Long id ) {
        Cart cart = cartDAO.findOneById( id );
        return new ResponseEntity<Cart>( cart, HttpStatus.OK );
    }

}
