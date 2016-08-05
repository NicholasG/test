package com.sombra.shop.cart.controller;

import com.sombra.shop.cart.domain.Cart;
import com.sombra.shop.cart.service.CartService;
import com.sombra.shop.user.service.CustomUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

import static com.sombra.shop.role.Role.ROLE_ADMIN;
import static com.sombra.shop.role.Role.ROLE_USER;

@RestController
@RequestMapping( value = "/cart" )
public class CartController {

    private final CartService cartService;

    private final CustomUserService userService;

    @Autowired
    public CartController( CartService cartService, CustomUserService userService ) {
        Assert.notNull( cartService, "cartService must not be null" );
        Assert.notNull( userService, "userService must not be null" );
        this.userService = userService;
        this.cartService = cartService;
    }

    @RequestMapping( method = RequestMethod.GET )
    @Secured( value = { ROLE_ADMIN, ROLE_USER } )
    public ResponseEntity<Cart> getOne( Principal principal ) {
        Long id = userService.getOneByUsername( principal.getName() ).getId();
        Cart cart = cartService.getOne( id );
        return new ResponseEntity<>( cart, HttpStatus.OK );
    }

    @RequestMapping( method = RequestMethod.POST )
    @Secured( value = { ROLE_ADMIN, ROLE_USER } )
    public ResponseEntity<Void> addToCart( @RequestParam( "goodId" ) Long goodId, Principal principal ) {
        Long id = userService.getOneByUsername( principal.getName() ).getId();
        cartService.addGoodToCart( goodId, id );
        return new ResponseEntity<>( HttpStatus.OK );
    }

    @RequestMapping( method = RequestMethod.DELETE )
    @Secured( value = { ROLE_ADMIN, ROLE_USER } )
    public ResponseEntity<Void> deleteFromCart( @RequestParam( "goodId" ) Long goodId, Principal principal ) {
        Long id = userService.getOneByUsername( principal.getName() ).getId();
        cartService.deleteGoodFromCart( goodId, id );
        return new ResponseEntity<>( HttpStatus.OK );
    }

}
