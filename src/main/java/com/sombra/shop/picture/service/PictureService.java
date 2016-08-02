package com.sombra.shop.picture.service;

import com.sombra.shop.picture.domain.Picture;

import java.util.List;

public interface PictureService {

    Picture add( Picture picture );

    void delete( Long id );

    Picture getOne( Long id );

    List<Picture> getAllByGoodId( Long goodId );

}
