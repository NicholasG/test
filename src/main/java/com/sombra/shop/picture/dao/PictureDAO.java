package com.sombra.shop.picture.dao;

import com.sombra.shop.dao.DAO;
import com.sombra.shop.picture.domain.Picture;
import com.sombra.shop.picture.mapper.PictureRowMapper;
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
public class PictureDAO implements DAO<Picture> {

    private static final Logger LOG = LoggerFactory.getLogger( PictureDAO.class );

    private static final String INSERT_QUERY = "INSERT INTO pictures " +
            "(image_as_base64, good_id) VALUES (?, ?)";

    private static final String DELETE_QUERY = "DELETE FROM pictures " +
            "WHERE id = ?";

    private static final String FIND_ONE_BY_ID_QUERY = "SELECT * FROM pictures " +
            "WHERE id = ?";

    private static final String SELECT_ALL_BY_GOOD_ID_QUERY = "SELECT * FROM pictures " +
            "WHERE good_id = ?";

    private final JdbcTemplate jdbcTemplate;

    private final PictureRowMapper rowMapper;

    @Autowired
    public PictureDAO( JdbcTemplate jdbcTemplate, PictureRowMapper rowMapper ) {
        Assert.notNull( jdbcTemplate, "jdbcTemplate must not be null" );
        Assert.notNull( rowMapper, "rowMapper must not be null" );
        this.rowMapper = rowMapper;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insert( Picture o ) {
        LOG.info( "Inserting a new picture for good with id='{}'", o.getGood().getId() );
        return jdbcTemplate.update( INSERT_QUERY,
                o.getImageAsString(),
                o.getGood().getId()
        );
    }

    /**
     * @param o the picture that's updating
     * @return the index of updated row
     * @deprecated The {@link Picture} cannot be updated.
     * Use {@link #insert(Picture)} or {@link #delete(Long)}
     */
    @Override
    @Deprecated
    public int update( Picture o ) {
        return 0;
    }

    @Override
    public void delete( Long id ) {
        LOG.info( "Deleting a picture with id='{}'", id );
        jdbcTemplate.update( DELETE_QUERY, id );
    }

    @Override
    public Picture findOneById( Long id ) {
        LOG.info( "Finding a picture by id='{}'", id );
        return jdbcTemplate.queryForObject( FIND_ONE_BY_ID_QUERY,
                new Object[]{ id },
                rowMapper
        );
    }

    public List<Picture> findAllByGoodId( Long goodId ) {
        LOG.info( "Finding all of pictures by good id='{}'", goodId );
        List<Picture> pictures = new ArrayList<>();
        List<Map<String, Object>> rows = jdbcTemplate
                .queryForList( SELECT_ALL_BY_GOOD_ID_QUERY, goodId );
        for ( Map<String, Object> row : rows ) {
            Picture picture = findOneById( ( Long ) row.get( "id" ) );
            pictures.add( picture );
        }
        return pictures;
    }

}
