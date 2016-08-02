package com.sombra.shop.good.service;

import com.sombra.shop.good.domain.Good;

import java.util.List;

public interface GoodService {

    int add( Good good );

    int update( Good good );

    void delete( Long id );

    Good getOne( Long id );

    List<Good> getAllByCartId( Long cartId );

}
