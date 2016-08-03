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
import java.util.*;

@Component
public class GoodRowMapper implements RowMapper<Good> {

    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String PRICE = "price";
    private static final String AVAILABLE = "available";
    private static final String DESCRIPTION = "description";
    private static final String CATEGORY_ID = "category_id";

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
        good.setId( resultSet.getLong( ID ) );
        good.setName( resultSet.getString( NAME ) );
        good.setPrice( resultSet.getDouble( PRICE ) );
        good.setAvailable( resultSet.getBoolean( AVAILABLE ) );
        good.setDescription( resultSet.getString( DESCRIPTION ) );

        long cID = resultSet.getLong( CATEGORY_ID );
        Category category = getCategory( cID );
        good.setCategory( category );

        good.setPictures( getPictures( good.getId() ) );

        return good;
    }

    public List<Good> mapRows( List<Map<String, Object>> rows ) {
        List<Good> goods = new ArrayList<>();
        for ( Map<String, Object> row : rows ) {
            Good good = new Good();
            good.setId( ( Long ) row.get( ID ) );
            good.setName( ( String ) row.get( NAME ) );
            good.setPrice( ( Double ) row.get( PRICE ) );
            good.setAvailable( ( Boolean ) row.get( AVAILABLE ) );
            good.setDescription( ( String ) row.get( DESCRIPTION ) );

            Long cID = ( Long ) row.get( CATEGORY_ID );
            good.setCategory( getCategory( cID ) );

            good.setPictures( getPictures( good.getId() ) );

            goods.add( good );
        }
        return goods;
    }

    private Category getCategory( long id ) {
        return categoryService.getOne( id );
    }

    private Set<Picture> getPictures( long id ) {
        List<Picture> pictures = pictureService.getAllByGoodId( id );
        return new HashSet<>( pictures );
    }

}
