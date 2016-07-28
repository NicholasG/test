package com.sombra.shop.category.dao;

import com.sombra.shop.category.domain.Category;
import com.sombra.shop.dao.DAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class CategoryDAO implements DAO<Category> {

    private static final Logger LOG = LoggerFactory.getLogger( CategoryDAO.class );

    private static final String INSERT_QUERY = "INSERT INTO categories (name) VALUES (?)";

    private static final String UPDATE_QUERY = "UPDATE categories " +
            "SET name = ? WHERE id = ?";

    private static final String DELETE_QUERY = "DELETE FROM categories WHERE id = ?";

    private static final String FIND_ONE_BY_ID_QUERY = "";

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CategoryDAO( JdbcTemplate jdbcTemplate ) {
        Assert.notNull( jdbcTemplate, "jdbcTemplate must not be null" );
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insert( Category o ) {
        LOG.info( "Inserting a new category with name='{}'", o.getName() );
        return jdbcTemplate.update( INSERT_QUERY, o.getName() );
    }

    @Override
    public int update( Category o ) {
        LOG.info( "Updating a category with id='{}'", o.getId() );
        return jdbcTemplate.update( UPDATE_QUERY,
                o.getName(),
                o.getId()
        );
    }

    @Override
    public void delete( Long id ) {
        LOG.info( "Deleting a category with id='{}'", id );
        jdbcTemplate.update( DELETE_QUERY, id );
    }

    @Override
    public Category findOneById( Long id ) {
        LOG.info( "Finding a category by id='{}'", id );
        return jdbcTemplate.queryForObject( FIND_ONE_BY_ID_QUERY,
                new Object[]{ id },
                new BeanPropertyRowMapper<Category>()
        );
    }

}
