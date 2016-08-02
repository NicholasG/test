package com.sombra.shop.good.mapper;

import com.sombra.shop.category.domain.Category;
import com.sombra.shop.category.service.CategoryService;
import com.sombra.shop.good.domain.Good;
import com.sombra.shop.picture.domain.Picture;
import com.sombra.shop.picture.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;

@Component
public class GoodRowMapper implements RowMapper<Good> {

    private final CategoryService categoryService;

    private final PictureService pictureService;

    @Autowired
    public GoodRowMapper( CategoryService categoryService, PictureService pictureService ) {
        Assert.notNull( categoryService, "categoryService must not be null" );
        Assert.notNull( pictureService, "pictureService must not be null" );
        this.categoryService = categoryService;
        this.pictureService = pictureService;
    }

    @Override
    public Good mapRow( ResultSet resultSet, int i ) throws SQLException {
        Good good = new Good();
        good.setId( resultSet.getLong( "id" ) );
        good.setName( resultSet.getString( "name" ) );
        good.setPrice( resultSet.getDouble( "price" ) );
        good.setAvailable( resultSet.getBoolean( "available" ) );
        good.setDescription( resultSet.getString( "description" ) );

        Category category = categoryService.getOne( resultSet.getLong( "category_id" ) );
        good.setCategory( category );

        List<Picture> pictures = pictureService.getAllByGoodId( good.getId() );
        good.setPictures( new HashSet<>( pictures ) );

        return good;
    }

}
