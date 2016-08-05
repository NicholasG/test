package com.sombra.shop.category.service;

import com.sombra.shop.category.domain.Category;

import java.util.List;

public interface CategoryService {

    List<Category> getAll();

    int add( Category category );

    int update( Category category );

    void delete( Long id );

    Category getOne( Long id );

}
