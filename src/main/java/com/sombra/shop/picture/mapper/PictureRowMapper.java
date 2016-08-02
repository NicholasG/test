package com.sombra.shop.picture.mapper;

import com.sombra.shop.picture.domain.Picture;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class PictureRowMapper implements RowMapper<Picture> {

    @Override
    public Picture mapRow( ResultSet resultSet, int i ) throws SQLException {
        Picture picture = new Picture();
        picture.setId( resultSet.getLong( "id" ) );
        picture.setImageAsString( resultSet.getString( "image_as_base64" ) );

        return picture;
    }

}
