package com.sombra.shop.user.service;

import com.sombra.shop.user.domain.CustomUser;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface CustomUserService extends UserDetailsService {

    CustomUser save( CustomUser user );

    void singIn( CustomUser user );

}
