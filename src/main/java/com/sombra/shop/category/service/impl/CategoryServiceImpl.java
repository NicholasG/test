package com.sombra.shop.category.service.impl;

import com.sombra.shop.category.dao.CategoryDAO;
import com.sombra.shop.category.domain.Category;
import com.sombra.shop.category.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryDAO dao;

    @Autowired
    public CategoryServiceImpl( CategoryDAO dao ) {
        Assert.notNull( dao, "dao must not be null" );
        this.dao = dao;
    }

    @Override
    public int add( Category category ) {
        return dao.insert( category );
    }

    @Override
    public int update( Category category ) {
        return dao.update( category );
    }

    @Override
    public void delete( Long id ) {
        dao.delete( id );
    }

    @Override
    public Category getOne( Long id ) {
        return dao.findOneById( id );
    }

}
