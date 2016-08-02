package com.sombra.shop.user.dao;

import com.sombra.shop.dao.DAO;
import com.sombra.shop.user.domain.CustomUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class CustomUserDAO implements DAO<CustomUser> {

    private static final Logger LOG = LoggerFactory.getLogger( CustomUserDAO.class );

    private static final String INSERT_QUERY = "INSERT INTO users " +
            "(username, password, first_name, last_name, active, role) " +
            "VALUES (?, ?, ?, ?, ?, ?)";

    private static final String UPDATE_QUERY = "UPDATE users " +
            "SET username = ?, password = ?, first_name = ?, last_name = ?, active = ?, role = ? " +
            "WHERE id = ?";

    private static final String DELETE_QUERY = "DELETE FROM users WHERE id = ?";

    private static final String FIND_ONE_BY_ID_QUERY = "SELECT * FROM users WHERE id = ?";

    private static final String FIND_ONE_BY_USERNAME_QUERY = "SELECT * FROM users WHERE LOWER(username) = LOWER(?)";

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CustomUserDAO( JdbcTemplate jdbcTemplate ) {
        Assert.notNull( jdbcTemplate, "jdbcTemplate must not be null" );
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insert( CustomUser o ) {
        LOG.info( "Inserting a new user with username='{}'", o.getUsername() );
        return jdbcTemplate.update( INSERT_QUERY,
                o.getUsername(),
                o.getPassword(),
                o.getFirstName(),
                o.getLastName(),
                o.isActive(),
                o.getRole()
        );
    }

    @Override
    public int update( CustomUser o ) {
        LOG.info( "Updating an user with id='{}'", o.getId() );
        return jdbcTemplate.update( UPDATE_QUERY,
                o.getUsername(),
                o.getPassword(),
                o.getFirstName(),
                o.getLastName(),
                o.isActive(),
                o.getRole(),
                o.getId()
        );
    }

    @Override
    public void delete( Long id ) {
        LOG.info( "Deleting an user with id='{}'", id );
        jdbcTemplate.update( DELETE_QUERY, id );
    }

    @Override
    public CustomUser findOneById( Long id ) {
        LOG.info( "Finding an user by id='{}'", id );
        return jdbcTemplate.queryForObject( FIND_ONE_BY_ID_QUERY,
                new Object[]{ id },
                new BeanPropertyRowMapper<>( CustomUser.class ) );
    }

    public CustomUser findOneByUsername( String username ) {
        LOG.info( "Finding an user by username='{}'", username );
        return jdbcTemplate.queryForObject( FIND_ONE_BY_USERNAME_QUERY,
                new Object[]{ username },
                new BeanPropertyRowMapper<>( CustomUser.class ) );
    }

}
