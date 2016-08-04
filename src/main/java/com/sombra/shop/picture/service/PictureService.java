package com.sombra.shop.picture.service;

import com.sombra.shop.good.domain.Good;
import com.sombra.shop.picture.domain.Picture;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface PictureService {

    int add( Good good, MultipartFile picture ) throws IOException;

    void delete( Long id );

    Picture getOne( Long id );

    List<Picture> getAllByGoodId( Long goodId );

}
