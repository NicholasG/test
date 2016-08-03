package com.sombra.shop.user.controller;

import com.sombra.shop.user.dao.CustomUserDAO;
import com.sombra.shop.user.domain.CustomUser;
import com.sombra.shop.user.service.CustomUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

import static com.sombra.shop.role.Role.ROLE_ADMIN;
import static com.sombra.shop.role.Role.ROLE_USER;

@RestController
@RequestMapping( value = "/users" )
public class CustomUserController {

    private static final Logger LOG = LoggerFactory.getLogger( CustomUserController.class );

    private final CustomUserDAO userDAO;

    private final CustomUserService userService;

    @Autowired
    public CustomUserController( CustomUserDAO userDAO, CustomUserService userService ) {
        Assert.notNull( userDAO, "userDAO must not be null" );
        Assert.notNull( userService, "userService must not be null" );
        this.userDAO = userDAO;
        this.userService = userService;
    }

    @RequestMapping( method = RequestMethod.GET )
    @Secured( value = { ROLE_ADMIN } )
    public ResponseEntity<List<CustomUser>> getAll() {
        return new ResponseEntity<>( userService.getAll(), HttpStatus.OK );
    }

    @RequestMapping(
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Void> singUp( @RequestBody CustomUser user ) {
        userService.save( user );
        return new ResponseEntity<>( HttpStatus.OK );
    }

    @RequestMapping( method = RequestMethod.PUT )
    @Secured( { ROLE_ADMIN } )
    public ResponseEntity<Void> editUser( @RequestBody CustomUser user ) {
        userService.edit( user );
        return new ResponseEntity<>( HttpStatus.OK );
    }

    @RequestMapping( method = RequestMethod.DELETE )
    @Secured( { ROLE_ADMIN } )
    public ResponseEntity<Void> changeActiveStatus( @RequestParam( "id" ) Long id ) {
        userService.changeActiveStatus( id );
        return new ResponseEntity<>( HttpStatus.OK );
    }

    @RequestMapping(
            value = "/change-password",
            method = RequestMethod.POST
    )
    @Secured( value = { ROLE_ADMIN, ROLE_USER } )
    public ResponseEntity<Void> changePassword( Principal principal,
                                                @RequestParam( "oldPass" ) String oldPass,
                                                @RequestParam( "newPass" ) String newPass ) {
        userService.changePassword( principal.getName(), oldPass, newPass );
        return new ResponseEntity<>( HttpStatus.OK );
    }

    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.GET
    )
    @Secured( value = { ROLE_ADMIN } )
    public ResponseEntity<CustomUser> getOne( @PathVariable( "id" ) Long id ) {
        return new ResponseEntity<>( userService.getOne( id ), HttpStatus.OK );
    }

    @RequestMapping(
            value = "/current",
            method = RequestMethod.GET
    )
    @Secured( value = { ROLE_ADMIN, ROLE_USER } )
    public ResponseEntity<CustomUser> getCurrentUser( Principal principal ) {
        LOG.info( "/users/current" );
        CustomUser user = userDAO.findOneByUsername( principal.getName() );
        return new ResponseEntity<>( user, HttpStatus.OK );
    }

}
