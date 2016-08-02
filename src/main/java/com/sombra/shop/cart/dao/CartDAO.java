package com.sombra.shop.cart.dao;

import com.sombra.shop.cart.domain.Cart;
import com.sombra.shop.cart.mapper.CartRowMapper;
import com.sombra.shop.dao.DAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class CartDAO implements DAO<Cart> {

    private static final Logger LOG = LoggerFactory.getLogger( CartDAO.class );

    private static final String INSERT_QUERY = "INSERT INTO carts " +
            "(amount, user_id) VALUES (?, ?)";

    private static final String UPDATE_QUERY = "";

    private static final String DELETE_QUERY = "";

    private static final String FIND_ONE_BY_ID_QUERY = "SELECT * FROM carts WHERE user_id = ?";

    private final JdbcTemplate jdbcTemplate;

    private final CartRowMapper rowMapper;

    @Autowired
    public CartDAO( JdbcTemplate jdbcTemplate, CartRowMapper rowMapper ) {
        Assert.notNull( jdbcTemplate, "jdbcTemplate must not be null" );
        Assert.notNull( rowMapper, "rowMapper must not be null" );
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
    }

    @Override
    public int insert( Cart o ) {
        LOG.info( "Inserting a cart" );
        return jdbcTemplate.update( INSERT_QUERY,
                o.getAmount(),
                o.getUser().getId()
        );
    }

    @Override
    @Deprecated
    public int update( Cart o ) {
        return 0;
    }

    @Override
    @Deprecated
    public void delete( Long id ) {

    }

    @Override
    public Cart findOneById( Long id ) {
        LOG.info( "Finding a cart by id='{}'", id );
        return jdbcTemplate.queryForObject( FIND_ONE_BY_ID_QUERY,
                new Object[]{ id },
                rowMapper
        );
    }

}
