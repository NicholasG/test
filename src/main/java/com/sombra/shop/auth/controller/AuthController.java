package com.sombra.shop.auth.controller;

import com.sombra.shop.user.domain.CustomUser;
import com.sombra.shop.user.service.CustomUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import static com.sombra.shop.role.Role.ROLE_USER;

@RestController
@RequestMapping( value = "/auth" )
public class AuthController {

    private final CustomUserService userService;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthController( CustomUserService userService, PasswordEncoder passwordEncoder ) {
        Assert.notNull( userService, "userService must not be null" );
        Assert.notNull( passwordEncoder, "passwordEncoder must not be null" );
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    @RequestMapping(
            value = "/login",
            method = RequestMethod.POST
    )
    public ResponseEntity<CustomUser> login( @RequestParam( "username" ) String username,
                                             @RequestParam( "password" ) String password ) {
        CustomUser user = userService.getOneByUsername( username );
        if ( passwordEncoder.matches( password, user.getPassword() ) ) {
            userService.singIn( user );
            return new ResponseEntity<>( user, HttpStatus.OK );
        } else {
            return new ResponseEntity<>( ( CustomUser ) null, HttpStatus.UNAUTHORIZED );
        }
    }

    @RequestMapping(
            value = "/register",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Void> register( @RequestBody CustomUser user ) {
        user.setRole( ROLE_USER );
        user.setActive( true );
        userService.save( user );
        return new ResponseEntity<>( HttpStatus.CREATED );
    }

}
