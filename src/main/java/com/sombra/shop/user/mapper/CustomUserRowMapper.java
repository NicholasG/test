package com.sombra.shop.user.mapper;

import com.sombra.shop.user.domain.CustomUser;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class CustomUserRowMapper implements RowMapper<CustomUser> {

    private static final String ID = "id";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String FIRST_NAME = "first_name";
    private static final String LAST_NAME = "last_name";
    private static final String ROLE = "role";
    private static final String ACTIVE = "active";

    @Override
    public CustomUser mapRow( ResultSet resultSet, int i ) throws SQLException {
        CustomUser user = new CustomUser(
                resultSet.getString( USERNAME ),
                resultSet.getString( FIRST_NAME ),
                resultSet.getString( LAST_NAME ),
                resultSet.getString( PASSWORD ),
                resultSet.getString( ROLE ),
                resultSet.getBoolean( ACTIVE )
        );
        user.setId( resultSet.getLong( ID ) );

        return user;
    }

    public List<CustomUser> mapRows( List<Map<String, Object>> rows ) {
        List<CustomUser> users = new ArrayList<>();
        for ( Map<String, Object> row : rows ) {
            CustomUser user = new CustomUser(
                    ( String ) row.get( USERNAME ),
                    ( String ) row.get( FIRST_NAME ),
                    ( String ) row.get( LAST_NAME ),
                    ( String ) row.get( PASSWORD ),
                    ( String ) row.get( ROLE ),
                    ( Boolean ) row.get( ACTIVE )
            );
            user.setId( ( Long ) row.get( ID ) );
            users.add( user );
        }

        return users;
    }

}
