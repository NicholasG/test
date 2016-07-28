package com.sombra.shop.user.service.impl;

import com.sombra.shop.user.dao.CustomUserDAO;
import com.sombra.shop.user.domain.CustomUser;
import com.sombra.shop.user.service.CustomUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Collections;

@Service
public class CustomUserServiceImpl implements CustomUserService {

    private final CustomUserDAO userDAO;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public CustomUserServiceImpl( PasswordEncoder passwordEncoder, CustomUserDAO userDAO ) {
        Assert.notNull( passwordEncoder, "passwordEncoder must not be null" );
        Assert.notNull( userDAO, "userDAO must not be null" );
        this.passwordEncoder = passwordEncoder;
        this.userDAO = userDAO;
    }

    @Override
    public UserDetails loadUserByUsername( String username ) throws UsernameNotFoundException {
        CustomUser user = userDAO.findOneByUsername( username );
        if ( user == null ) {
            throw new UsernameNotFoundException( String.format( "User '%s' not found", username ) );
        } else {
            return createUser( user );
        }
    }

    @Override
    public CustomUser save( CustomUser user ) {
        user.setPassword( passwordEncoder.encode( user.getPassword() ) );
        int id = userDAO.insert( user );
        user.setId( ( long ) id );

        return user;
    }

    @Override
    public void singIn( CustomUser user ) {
        SecurityContextHolder.getContext()
                .setAuthentication( authenticate( user ) );
    }

    private Authentication authenticate( CustomUser user ) {
        return new UsernamePasswordAuthenticationToken(
                createUser( user ),
                null,
                Collections.singleton( createAuthority( user ) )
        );
    }

    private User createUser( CustomUser user ) {
        return new User(
                user.getUsername(),
                user.getPassword(),
                user.isActive(),
                true, true, true,
                Collections.singleton( createAuthority( user ) )
        );
    }

    private GrantedAuthority createAuthority( CustomUser user ) {
        return new SimpleGrantedAuthority( user.getRole() );
    }

}
