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

import java.util.List;

@Component
public class CartDAO implements DAO<Cart> {

    private static final Logger LOG = LoggerFactory.getLogger( CartDAO.class );

    private static final String INSERT_QUERY = "INSERT INTO carts " +
            "(amount, user_id) VALUES (?, ?)";

    @Deprecated
    private static final String UPDATE_QUERY = "";

    @Deprecated
    private static final String DELETE_QUERY = "";

    private static final String FIND_ONE_BY_ID_QUERY = "SELECT * FROM carts WHERE user_id = ?";

    private static final String ADD_GOOD_TO_CART_QUERY = "INSERT INTO goods_carts " +
            "(goods_id, carts_user_id) VALUES (?, ?)";

    private static final String DELETE_GOOD_FROM_CART_QUERY = "DELETE FROM goods_carts " +
            "WHERE goods_id = ? AND carts_user_id = ?";

    private final JdbcTemplate jdbcTemplate;

    private final CartRowMapper rowMapper;

    @Autowired
    public CartDAO( JdbcTemplate jdbcTemplate, CartRowMapper rowMapper ) {
        Assert.notNull( jdbcTemplate, "jdbcTemplate must not be null" );
        Assert.notNull( rowMapper, "rowMapper must not be null" );
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
    }

    /**
     * @return null
     * @deprecated
     */
    @Override
    public List<Cart> findAll() {
        return null;
    }

    @Override
    public int insert( Cart o ) {
        LOG.info( "Inserting a cart" );
        return jdbcTemplate.update( INSERT_QUERY,
                o.getAmount(),
                o.getUser().getId()
        );
    }

    /**
     * @param o the cart
     * @return 0
     * @deprecated
     */
    @Override
    @Deprecated
    public int update( Cart o ) {
        return 0;
    }

    /**
     * @param id of the cart
     * @deprecated
     */
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

    public int addGoodToCart( Long goodId, Long cartId ) {
        LOG.info( "Adding a good with id='{}' to a cart id='{}'", goodId, cartId );
        return jdbcTemplate.update( ADD_GOOD_TO_CART_QUERY,
                goodId, cartId
        );
    }

    public void deleteGoodFromCart( Long goodId, Long cartId ) {
        LOG.info( "Deleting a good with id='{}' from a cart id='{}'", goodId, cartId );
        jdbcTemplate.update( DELETE_GOOD_FROM_CART_QUERY,
                goodId, cartId
        );
    }

}
