package com.sombra.shop.category.mapper;

import com.sombra.shop.category.domain.Category;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class CategoryRowMapper implements RowMapper<Category> {

    private static final String ID = "id";
    private static final String NAME = "name";

    @Override
    public Category mapRow( ResultSet resultSet, int i ) throws SQLException {
        Category category = new Category();
        category.setId( resultSet.getLong( ID ) );
        category.setName( resultSet.getString( NAME ) );

        return category;
    }

    public List<Category> mapRows( List<Map<String, Object>> rows ) {
        List<Category> categories = new ArrayList<>();
        for ( Map<String, Object> row : rows ) {
            Category category = new Category();
            category.setId( ( Long ) row.get( ID ) );
            category.setName( ( String ) row.get( NAME ) );

            categories.add( category );
        }
        return categories;
    }

}
