package com.sombra.shop.dao;

public interface DAO<T> {

    int insert( T o );

    int update( T o );

    void delete( Long id );

    T findOneById( Long id );

}
