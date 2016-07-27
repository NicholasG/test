package com.sombra.shop.dao;

import javax.transaction.Transactional;

@Transactional
public interface DAO<T> {

    int insert( T o );

    int update( T o );

    void delete( Long id );

    T findOneById( Long id );

}
