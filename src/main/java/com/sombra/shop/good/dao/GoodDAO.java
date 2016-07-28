package com.sombra.shop.good.dao;

import com.sombra.shop.dao.DAO;
import com.sombra.shop.good.domain.Good;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class GoodDAO implements DAO<Good> {

    private static final Logger LOG = LoggerFactory.getLogger( GoodDAO.class );

    private static final String INSERT_QUERY = "INSERT INTO goods " +
            "(available, description, name, price, category_id) " +
            "VALUES (?, ?, ?, ?, ?)";

    private static final String UPDATE_QUERY = "UPDATE goods " +
            "SET available = ?, description = ?, name = ?, price = ?, category_id = ? " +
            "WHERE id = ?";

    private static final String DELETE_QUERY = "DELETE FROM goods WHERE id = ?";

    private static final String FIND_ONE_BY_ID_QUERY = "SELECT * FROM goods WHERE id = ?";

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public GoodDAO( JdbcTemplate jdbcTemplate ) {
        Assert.notNull( jdbcTemplate, "jdbcTemplate must not be null" );
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insert( Good o ) {
        LOG.info( "Inserting a good with name='{}'", o.getName() );
        return jdbcTemplate.update( INSERT_QUERY,
                o.isAvailable(),
                o.getDescription(),
                o.getName(),
                o.getPrice(),
                o.getCategory().getId()
        );
    }

    @Override
    public int update( Good o ) {
        LOG.info( "Updating a good with id='{}'", o.getId() );
        return jdbcTemplate.update( UPDATE_QUERY,
                o.isAvailable(),
                o.getDescription(),
                o.getName(),
                o.getPrice(),
                o.getCategory().getId()
        );
    }

    @Override
    public void delete( Long id ) {
        LOG.info( "Deleting a good by id='{}'", id );
        jdbcTemplate.update( DELETE_QUERY, id );
    }

    @Override
    public Good findOneById( Long id ) {
        LOG.info( "Finding a good by id='{}'" );
        return jdbcTemplate.queryForObject( FIND_ONE_BY_ID_QUERY,
                new Object[]{ id },
                new BeanPropertyRowMapper<>( Good.class )
        );
    }

}
