package com.sombra.shop.category.service;

import com.sombra.shop.category.domain.Category;

public interface CategoryService {

    int add( Category category );

    int update( Category category );

    void delete( Long id );

    Category getOne( Long id );

}
