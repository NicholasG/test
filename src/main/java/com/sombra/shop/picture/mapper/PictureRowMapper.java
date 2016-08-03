package com.sombra.shop.picture.mapper;

import com.sombra.shop.picture.domain.Picture;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class PictureRowMapper implements RowMapper<Picture> {

    private static final String ID = "id";
    private static final String IMAGE_AS_BASE_64 = "image_as_base64";

    @Override
    public Picture mapRow( ResultSet resultSet, int i ) throws SQLException {
        Picture picture = new Picture();
        picture.setId( resultSet.getLong( ID ) );
        picture.setImageAsString( resultSet.getString( IMAGE_AS_BASE_64 ) );

        return picture;
    }

    public List<Picture> mapRows( List<Map<String, Object>> rows ) {
        List<Picture> pictures = new ArrayList<>();
        for ( Map<String, Object> row : rows ) {
            Picture picture = new Picture();
            picture.setId( ( Long ) row.get( ID ) );
            picture.setImageAsString( ( String ) row.get( IMAGE_AS_BASE_64 ) );
            pictures.add( picture );
        }
        return pictures;
    }

}
