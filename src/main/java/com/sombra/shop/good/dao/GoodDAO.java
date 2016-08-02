package com.sombra.shop.good.dao;

import com.sombra.shop.dao.DAO;
import com.sombra.shop.good.domain.Good;
import com.sombra.shop.good.mapper.GoodRowMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    private static final String SELECT_ALL_BY_CART_ID_QUERY = "SELECT goods_id FROM goods_carts " +
            "WHERE carts_user_id = ?";

    private final JdbcTemplate jdbcTemplate;

    private final GoodRowMapper rowMapper;

    @Autowired
    public GoodDAO( JdbcTemplate jdbcTemplate, GoodRowMapper rowMapper ) {
        Assert.notNull( jdbcTemplate, "jdbcTemplate must not be null" );
        Assert.notNull( rowMapper, "rowMapper must not be null" );
        this.rowMapper = rowMapper;
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
        LOG.info( "Finding a good by id='{}'", id );
        return jdbcTemplate.queryForObject( FIND_ONE_BY_ID_QUERY,
                new Object[]{ id },
                rowMapper
        );
    }

    public List<Good> findAllByCartId( Long cartId ) {
        LOG.info( "Finding all of goods by cart id='{}'", cartId );
        List<Good> goods = new ArrayList<>();
        List<Map<String, Object>> rows = jdbcTemplate
                .queryForList( SELECT_ALL_BY_CART_ID_QUERY, cartId );
        for ( Map<String, Object> row : rows ) {
            Good good = findOneById( ( Long ) row.get( "goods_id" ) );
            goods.add( good );
        }
        return goods;
    }

}
