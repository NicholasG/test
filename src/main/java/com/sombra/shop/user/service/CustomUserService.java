package com.sombra.shop.user.service;

import com.sombra.shop.user.domain.CustomUser;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface CustomUserService extends UserDetailsService {

    List<CustomUser> getAll();

    CustomUser getOne( Long id );

    CustomUser getOneByUsername( String username );

    CustomUser save( CustomUser user );

    CustomUser edit( CustomUser user );

    void changeActiveStatus( Long id );

    void singIn( CustomUser user );

    void changePassword( String username, String oldPass, String newPass );

}
