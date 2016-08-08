package com.sombra.shop.user.dao;

import com.sombra.shop.dao.DAO;
import com.sombra.shop.user.domain.CustomUser;
import com.sombra.shop.user.mapper.CustomUserRowMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Map;

@Component
public class CustomUserDAO implements DAO<CustomUser> {

    private static final Logger LOG = LoggerFactory.getLogger( CustomUserDAO.class );

    private static final String SELECT_ALL_QUERY = "SELECT * FROM users";

    private static final String INSERT_QUERY = "INSERT INTO users " +
            "(username, password, first_name, last_name, active, role) " +
            "VALUES (?, ?, ?, ?, ?, ?)";

    private static final String UPDATE_QUERY = "UPDATE users " +
            "SET username = ?, first_name = ?, last_name = ?, active = ?, role = ? " +
            "WHERE id = ?";

    private static final String DELETE_QUERY = "DELETE FROM users WHERE id = ?";

    private static final String SELECT_ONE_BY_ID_QUERY = "SELECT * FROM users WHERE id = ?";

    private static final String SELECT_ONE_BY_USERNAME_QUERY = "SELECT * FROM users " +
            "WHERE LOWER(username) = LOWER(?)";

    private static final String UPDATE_PASSWORD_QUERY = "UPDATE users SET password = ? WHERE id = ?";

    private final JdbcTemplate jdbcTemplate;

    private final CustomUserRowMapper rowMapper;

    @Autowired
    public CustomUserDAO( JdbcTemplate jdbcTemplate, CustomUserRowMapper rowMapper ) {
        Assert.notNull( jdbcTemplate, "jdbcTemplate must not be null" );
        Assert.notNull( rowMapper, "rowMapper must not be null" );
        this.rowMapper = rowMapper;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<CustomUser> findAll() {
        LOG.info( "Finding all of users" );
        List<Map<String, Object>> rows = jdbcTemplate.queryForList( SELECT_ALL_QUERY );
        return rowMapper.mapRows( rows );
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
        return jdbcTemplate.queryForObject( SELECT_ONE_BY_ID_QUERY,
                new Object[]{ id },
                rowMapper );
    }

    public CustomUser findOneByUsername( String username ) {
        LOG.info( "Finding an user by username='{}'", username );
        return jdbcTemplate.queryForObject( SELECT_ONE_BY_USERNAME_QUERY,
                new Object[]{ username },
                rowMapper );
    }

    public void changePassword( CustomUser user ) {
        jdbcTemplate.update( UPDATE_PASSWORD_QUERY,
                user.getPassword(),
                user.getId()
        );
        LOG.info( "Password for user='{}' has been changed", user.getUsername() );
    }

}
