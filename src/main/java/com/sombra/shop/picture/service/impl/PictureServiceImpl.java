package com.sombra.shop.picture.service.impl;

import com.sombra.shop.good.domain.Good;
import com.sombra.shop.picture.dao.PictureDAO;
import com.sombra.shop.picture.domain.Picture;
import com.sombra.shop.picture.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
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
    public int add( Good good, MultipartFile picture ) throws IOException {
        Picture p = new Picture();
        String s = Base64.getEncoder().encodeToString( picture.getBytes() );
        p.setImageAsString( s );
        p.setGood( good );
        return dao.insert( p );
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
