package com.sombra.shop.picture.service.impl;

import com.sombra.shop.picture.dao.PictureDAO;
import com.sombra.shop.picture.domain.Picture;
import com.sombra.shop.picture.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class PictureServiceImpl implements PictureService {

    private final PictureDAO dao;

    @Autowired
    public PictureServiceImpl( PictureDAO dao ) {
        Assert.notNull( dao, "dao must not be null" );
        this.dao = dao;
    }

    @Override
    public Picture add( Picture picture ) {
        Long newId = ( long ) dao.insert( picture );
        picture.setId( newId );
        return picture;
    }

    @Override
    public void delete( Long id ) {
        dao.delete( id );
    }

    @Override
    public Picture getOne( Long id ) {
        return dao.findOneById( id );
    }

    @Override
    public List<Picture> getAllByGoodId( Long goodId ) {
        return dao.findAllByGoodId( goodId );
    }

}
