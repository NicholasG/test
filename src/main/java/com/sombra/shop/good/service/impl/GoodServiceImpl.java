package com.sombra.shop.good.service.impl;

import com.sombra.shop.good.dao.GoodDAO;
import com.sombra.shop.good.domain.Good;
import com.sombra.shop.good.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class GoodServiceImpl implements GoodService {

    private final GoodDAO dao;

    @Autowired
    public GoodServiceImpl( GoodDAO dao ) {
        Assert.notNull( dao, "dao must not be null" );
        this.dao = dao;
    }

    @Override
    public int add( Good good ) {
        return dao.insert( good );
    }

    @Override
    public int update( Good good ) {
        return dao.update( good );
    }

    @Override
    public void delete( Long id ) {
        dao.delete( id );
    }

    @Override
    public Good getOne( Long id ) {
        return dao.findOneById( id );
    }

    @Override
    public List<Good> getAllByCartId( Long cartId ) {
        return dao.findAllByCartId( cartId );
    }

}
