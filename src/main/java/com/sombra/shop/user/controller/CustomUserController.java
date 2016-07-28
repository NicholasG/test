package com.sombra.shop.user.controller;

import com.sombra.shop.role.Role;
import com.sombra.shop.user.dao.CustomUserDAO;
import com.sombra.shop.user.domain.CustomUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping( value = "/users" )
public class CustomUserController {

    private static final Logger LOG = LoggerFactory.getLogger( CustomUserController.class );

    private final CustomUserDAO userDAO;

    @Autowired
    public CustomUserController( CustomUserDAO userDAO ) {
        Assert.notNull( userDAO, "userDAO must not be null" );
        this.userDAO = userDAO;
    }

    @RequestMapping(
            value = "/current",
            method = RequestMethod.GET
    )
    @Secured( value = { Role.ROLE_ADMIN, Role.ROLE_USER } )
    public ResponseEntity<CustomUser> getCurrentUser( Principal principal ) {
        LOG.info( "/users/current" );
        CustomUser user = userDAO.findOneByUsername( principal.getName() );
        return new ResponseEntity<>( user, HttpStatus.OK );
    }

}
