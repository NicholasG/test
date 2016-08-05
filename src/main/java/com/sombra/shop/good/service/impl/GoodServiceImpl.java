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

    private final GoodDAO goodDAO;

    @Autowired
    public GoodServiceImpl( GoodDAO goodDAO ) {
        Assert.notNull( goodDAO, "goodDAO must not be null" );
        this.goodDAO = goodDAO;
    }

    @Override
    public List<Good> getAll() {
        return goodDAO.findAll();
    }

    @Override
    public int add( Good good ) {
        return goodDAO.insert( good );
    }

    @Override
    public int update( Good good ) {
        return goodDAO.update( good );
    }

    @Override
    public void delete( Long id ) {
        goodDAO.delete( id );
    }

    @Override
    public Good getOne( Long id ) {
        return goodDAO.findOneById( id );
    }

    @Override
    public List<Good> getAllByCartId( Long cartId ) {
        return goodDAO.findAllByCartId( cartId );
    }

    @Override
    public List<Good> getAllByCategoryId( Long categoryId ) {
        return goodDAO.findAllByCategoryId( categoryId );
    }

    @Override
    public List<Good> getAllByName( String name ) {
        return goodDAO.findAllByName( name );
    }

    @Override
    public List<Good> getAllByNameAndCategoryId( String name, Long categoryId ) {
        return goodDAO.findAllByNameAndCategoryId( name, categoryId );
    }

}
