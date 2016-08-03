package com.sombra.shop.dao;

import java.util.List;

public interface DAO<T> {

    List<T> findAll();

    int insert( T o );

    int update( T o );

    void delete( Long id );

    T findOneById( Long id );

}
